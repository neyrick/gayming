package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.MetaCharacter;

public interface CharacterFactory<T extends GameCharacter> {

	public T createCharacter(MetaCharacter metaCharacter);

	public boolean isValid(MetaCharacter character);
}
