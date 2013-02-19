package fr.neyrick.karax.rest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.servlet.ServletContextURIResolver;

import fr.neyrick.karax.data.MetaCharacterRepository;
import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.RulesetLiteral;
/**
 * Servlet implementation class FopServlet
 */
@WebServlet(description = "HTML Page for desktop/mobile viewing", urlPatterns = { "/htmlchar" })
public class CharacterHTMLPageServlet extends HttpServlet {
    private static final long serialVersionUID = -908918093488215264L;

    protected static final String CHARID_REQUEST_PARAM = "char";

    protected static final String LANG_REQUEST_PARAM = "lang";

    protected static final String DISPLAY_REQUEST_PARAM = "display";

    protected static final String SMALL_MOBILE_MODE = "smallMobile";
    protected static final String LARGE_MOBILE_MODE = "largeMobile";
    protected static final String DESKTOP_MODE = "desktop";
    
    protected static final Map<String, String> displayModeMap = new HashMap<>();
    
    static {
    	displayModeMap.put(SMALL_MOBILE_MODE, "smallMobile");
    	displayModeMap.put(LARGE_MOBILE_MODE, "largeMobile");
    	displayModeMap.put(DESKTOP_MODE, "desktop");
    }
    
    /** The TransformerFactory used to create Transformer instances */
    protected TransformerFactory transFactory = null;
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
            String displayParam = request.getParameter(DISPLAY_REQUEST_PARAM);
            String displaySheet = null;
            if (displayParam != null) displaySheet = displayModeMap.get(displayParam);
            response.setContentType("text/html");

            if ((charIdParam != null) && (displaySheet != null)) {
                renderHTML(Long.parseLong(charIdParam), displaySheet, request.getLocale(), response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Error</title></head>\n"
                          + "<body><h1>FopServlet Error</h1><h3>Invalid 'char' or 'display'"
                          + "request param given.</body></html>");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private File getCharacterSheetFile(String styleSheetDir, String styleSheetFile, Locale locale) {
    	File xslFile = new File(xslPath + "/" + styleSheetDir, styleSheetFile + "." + locale.getLanguage().toLowerCase() + ".xsl");
    	if (xslFile.exists()) return xslFile;
    	else return new File(xslPath + "/" + styleSheetDir, styleSheetFile + ".xsl");
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

    protected void renderHTML(long charId, String displaySheet, Locale locale, HttpServletResponse response)
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
        
		Source xslSource = new StreamSource(getCharacterSheetFile(character.getGame().getStylesheet(), displaySheet, locale));

        //Setup the XSL transformation
        Transformer transformer = this.transFactory.newTransformer(xslSource);
        transformer.setURIResolver(this.uriResolver);

        //Start the transformation and rendering process
        transformer.transform(xmlSrc, new StreamResult(response.getOutputStream()));
    }

}
