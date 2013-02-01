package fr.neyrick.karax.model.eclipsephase;

import fr.neyrick.karax.model.AbstractCharacterFactory;
import fr.neyrick.karax.model.Character;
import fr.neyrick.karax.model.CharacterFactory;

public class EclipsePhaseCharacterFactory extends AbstractCharacterFactory
		implements CharacterFactory {

	@Override
	public Character createCharacter() {
		return new EclipsePhaseCharacter();
	}

}
