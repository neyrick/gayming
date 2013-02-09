package fr.neyrick.karax.fop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.servlet.ServletContextURIResolver;
import org.xml.sax.SAXException;

import fr.neyrick.karax.data.MetaCharacterRepository;
import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.RulesetLiteral;
/**
 * Servlet implementation class FopServlet
 */
@WebServlet(description = "PDF generation using Fop", urlPatterns = { "/pdfgen" })
public class FopServlet extends HttpServlet {
    private static final long serialVersionUID = -908918093488215264L;

    /** Name of the parameter used for the XSL-FO file */
    protected static final String CHARID_REQUEST_PARAM = "char";

    /** The TransformerFactory used to create Transformer instances */
    protected TransformerFactory transFactory = null;
    /** The FopFactory used to create Fop instances */
    protected FopFactory fopFactory = null;
    /** URIResolver for use by this servlet */
    protected URIResolver uriResolver;

    protected String xslPath;
    
    @Inject
    private MetaCharacterRepository repository;

    @Inject @Any
    private Instance<CharacterFactory> characterFactoryInstance;
    

    /**
     * {@inheritDoc}
     */
    public void init() throws ServletException {
    	this.xslPath = getServletContext().getRealPath("/WEB-INF/xsl/");
    	this.uriResolver = new ServletContextURIResolver(getServletContext());
        this.transFactory = TransformerFactory.newInstance();
        this.transFactory.setURIResolver(this.uriResolver);
        //Configure FopFactory as desired
        this.fopFactory = FopFactory.newInstance();
        try {
			this.fopFactory.setUserConfig(new File(getServletContext().getRealPath("/WEB-INF"), "fop.xconf"));
		} catch (SAXException | IOException e) {
			throw new ServletException(e);
		}
         this.fopFactory.setSourceResolution(300);
         this.fopFactory.setURIResolver(uriResolver);
        configureFopFactory();
    }

    /**
     * This method is called right after the FopFactory is instantiated and can be overridden
     * by subclasses to perform additional configuration.
     */
    protected void configureFopFactory() {
        //Subclass and override this method to perform additional configuration
    }

    public GameCharacter lookupCharacterById( long id, Locale locale) {
    	MetaCharacter metaCharacter = repository.findCompleteById(id);
        if (metaCharacter == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
		RulesetLiteral literal = new RulesetLiteral(metaCharacter.getGame().getRuleset());
		CharacterFactory factory = characterFactoryInstance.select(literal).get();
		if (factory == null) {
			throw new WebApplicationException(new IllegalArgumentException("No factory for this character"));
		}
        return factory.createCharacter(metaCharacter, locale);
    }
    
    /**
     * {@inheritDoc}
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException {
        try {
            //Get parameters
            String charIdParam = request.getParameter(CHARID_REQUEST_PARAM);

            if (charIdParam != null) {
                renderXML(Long.parseLong(charIdParam), request.getLocale(), response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Error</title></head>\n"
                          + "<body><h1>FopServlet Error</h1><h3>No 'char' "
                          + "request param given.</body></html>");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Converts a String parameter to a JAXP Source object.
     * @param param a String parameter
     * @return Source the generated Source object
     */
    protected Source convertString2Source(String param) {
        Source src;
        try {
            src = uriResolver.resolve(param, null);
        } catch (TransformerException e) {
            src = null;
        }
        if (src == null) {
            src = new StreamSource(new File(param));
        }
        return src;
    }

    private void sendPDF(byte[] content, HttpServletResponse response) throws IOException {
        //Send the result back to the client
        response.setContentType("application/pdf");
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
        response.getOutputStream().flush();
    }

    /**
     * Renders an XML file into a PDF file by applying a stylesheet
     * that converts the XML to XSL-FO. The PDF is written to a byte array
     * that is returned as the method's result.
     * @param xml the XML file
     * @param xslt the XSLT file
     * @param response HTTP response object
     * @throws FOPException If an error occurs during the rendering of the
     * XSL-FO
     * @throws TransformerException If an error occurs during XSL
     * transformation
     * @throws IOException In case of an I/O problem
     */
    protected void renderXML(long charId, Locale locale, HttpServletResponse response)
                throws FOPException, TransformerException, IOException {

    	GameCharacter character = lookupCharacterById(charId, locale);
    	
        // Source
        JAXBSource xmlSrc;
		try {
			JAXBContext jc = JAXBContext.newInstance(character.getClass());
			xmlSrc = new JAXBSource(jc, character);
		} catch (JAXBException e) {
			throw new WebApplicationException(e);
		}
        
		File xslFile = getCharacterSheetFile(character.getGame().getStylesheet(), locale);
		
		Source xslSource = new StreamSource(xslFile);
//		Source xslSource = new StreamSource(new File(xslPath, "fochain.xsl"));

        //Setup the XSL transformation
        Transformer transformer = this.transFactory.newTransformer(xslSource);
//        transformer.setParameter("filesdir", character.getGame().getStylesheet());
        transformer.setURIResolver(this.uriResolver);

        //Start transformation and rendering process
        render(xmlSrc, transformer, response);
    }

    private File getCharacterSheetFile(String styleSheetDir, Locale locale) {
    	File xslFile = new File(xslPath + "/" + styleSheetDir, "character_sheet." + locale.getLanguage().toLowerCase() + ".xsl");
    	if (xslFile.exists()) return xslFile;
    	else return new File(xslPath + "/" + styleSheetDir, "character_sheet.xsl");
    }
    
    /**
     * Renders an input file (XML or XSL-FO) into a PDF file. It uses the JAXP
     * transformer given to optionally transform the input document to XSL-FO.
     * The transformer may be an identity transformer in which case the input
     * must already be XSL-FO. The PDF is written to a byte array that is
     * returned as the method's result.
     * @param src Input XML or XSL-FO
     * @param transformer Transformer to use for optional transformation
     * @param response HTTP response object
     * @throws FOPException If an error occurs during the rendering of the
     * XSL-FO
     * @throws TransformerException If an error occurs during XSL
     * transformation
     * @throws IOException In case of an I/O problem
     */
    protected void render(Source src, Transformer transformer, HttpServletResponse response)
                throws FOPException, TransformerException, IOException {

        FOUserAgent foUserAgent = getFOUserAgent();

        //Setup output
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //Setup FOP
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

        //Make sure the XSL transformation's result is piped through to FOP
        Result res = new SAXResult(fop.getDefaultHandler());

        //Start the transformation and rendering process
        transformer.transform(src, res);

        //Return the result
        sendPDF(out.toByteArray(), response);
    }

    /** @return a new FOUserAgent for FOP */
    protected FOUserAgent getFOUserAgent() {
        FOUserAgent userAgent = fopFactory.newFOUserAgent();
        //Configure foUserAgent as desired
        return userAgent;
    }
}
