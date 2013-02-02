package fr.neyrick.karax.model.eclipsephase;

import javax.enterprise.context.ApplicationScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.Ruleset;
import fr.neyrick.karax.model.StringFeature;

@Ruleset("ECLIPSE_PHASE_1.0")
@ApplicationScoped
public class EclipsePhaseCharacterFactory extends CharacterFactory {

	@Override
	protected GameCharacter initCharacter(MetaCharacter metaCharacter) {
		EclipsePhaseCharacter character = new EclipsePhaseCharacter();
		character.setBackground(new StringFeature("BACKGROUND"));
		character.setFaction(new StringFeature("FACTION"));
		// TODO Auto-generated method stub
		return character;
	}


}
