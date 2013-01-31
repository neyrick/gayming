package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.MetaCharacter;

public interface CharacterFactory {

	public Character createCharacter();
	
	public void initCharacter(Character character, MetaCharacter metaCharacter);
}
