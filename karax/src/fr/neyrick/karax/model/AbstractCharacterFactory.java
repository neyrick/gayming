package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.MetaCharacter;

public abstract class AbstractCharacterFactory implements CharacterFactory {

	@Override
	public void initCharacter(Character character, MetaCharacter metaCharacter) {
		character.setCreationDate(metaCharacter.getCreationDate());
		character.setGame(metaCharacter.getGame());
		character.setMetaId(metaCharacter.getId());
		character.setName(metaCharacter.getName());
		character.setPlayerName(metaCharacter.getPlayerName());
		character.setExperience(metaCharacter.getExperienceTotal());
		character.setLastUpdate(metaCharacter.getLastUpdate());
	}

}
