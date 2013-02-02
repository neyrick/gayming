package fr.neyrick.karax.model;

import javax.enterprise.context.ApplicationScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;

@ApplicationScoped
public abstract class CharacterFactory {

	public GameCharacter createCharacter(MetaCharacter metaCharacter)  {
	    GameCharacter character = initCharacter(metaCharacter);
		setMetadata(character, metaCharacter);
		character.processEdits(metaCharacter.getEdits());
		return character;
	}
	
	protected abstract GameCharacter initCharacter(MetaCharacter metaCharacter);

	private void setMetadata(GameCharacter character, MetaCharacter metaCharacter) {
		character.setCreationDate(metaCharacter.getCreationDate());
		character.setGame(metaCharacter.getGame());
		character.setMetaId(metaCharacter.getId());
		character.setName(metaCharacter.getName());
		character.setPlayerName(metaCharacter.getPlayerName());
		character.setExperience(metaCharacter.getExperienceTotal());
		character.setLastUpdate(metaCharacter.getLastUpdate());
	}

}
