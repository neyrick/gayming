package fr.neyrick.karax.model.eclipsephase;

import javax.enterprise.context.ApplicationScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.AbstractCharacterFactory;
import fr.neyrick.karax.model.CharacterFactory;

@ApplicationScoped
public class EclipsePhaseCharacterFactory extends AbstractCharacterFactory<EclipsePhaseCharacter>
		implements CharacterFactory<EclipsePhaseCharacter> {

	private static final String GAME_KEY = "Eclipse Phase"; 
	
	@Override
	public boolean isValid(MetaCharacter character) {
		return character.getGame().getName().equalsIgnoreCase(GAME_KEY);
	}

	@Override
	protected void initCustomCharacter(EclipsePhaseCharacter character,
			MetaCharacter metaCharacter) {
		// TODO Auto-generated method stub
		
	}

	
}
