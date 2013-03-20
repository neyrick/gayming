package fr.neyrick.karax.eclipsephase.fop;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import fr.neyrick.karax.eclipsephase.data.MorphRepository;
import fr.neyrick.karax.eclipsephase.entities.MorphDefinition;
import fr.neyrick.karax.eclipsephase.model.EclipsePhaseCharacterFactory;
import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.fop.FopServlet;
import fr.neyrick.karax.model.CharacterFactory;
/**
 * Servlet implementation class FopServlet
 */
@WebServlet(description = "PDF generation using Fop", urlPatterns = { "/remorph" })
public class RemorphServlet extends FopServlet {
    private static final long serialVersionUID = -908918093488215264L;

    @Inject
    private MorphRepository morphRepository;
    
	private MorphDefinition findMorph(MetaCharacter character, Map<String, String[]> filterData) {
		MorphDefinition result = null;
		String[] values = filterData.get(EclipsePhaseCharacterFactory.FILTER_MORPH);
		if ((values != null) && (values.length > 0)) {
			String morphKey = values[0];
			if ((morphKey != null) && (!"".equals(morphKey))) {
				result = morphRepository.findCompleteByKey(morphKey);
			}
		}
		return result;
	}


    @Override
	protected void postProcessMetaCharacter(CharacterFactory factory, MetaCharacter character,
			Map<String, String[]> filterParams) {
		super.postProcessMetaCharacter(factory, character, filterParams);
    	MorphDefinition morph = findMorph(character, filterParams);
    	if (morph != null) {
    		((EclipsePhaseCharacterFactory)factory).remorph(character, morph);
    	}
	}

}
