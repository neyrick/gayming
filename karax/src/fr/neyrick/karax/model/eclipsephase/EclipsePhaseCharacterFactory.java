package fr.neyrick.karax.model.eclipsephase;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import fr.neyrick.karax.entities.generic.MetaCharacter;
import fr.neyrick.karax.model.CharacterFactory;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.Ruleset;
import fr.neyrick.karax.model.SimpleContainerFeature;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableNumericFeature;

@Ruleset("ECLIPSE_PHASE_1.0")
@RequestScoped
public class EclipsePhaseCharacterFactory extends CharacterFactory {

	@Override
	protected GameCharacter initCharacter(MetaCharacter metaCharacter) {
		EclipsePhaseCharacter character = new EclipsePhaseCharacter();
		character.setBackground(registerListener(new StringFeature("BACKGROUND")));
		character.setFaction(registerListener(new StringFeature("FACTION")));
		
		FeatureCalculator<VariableNumericFeature> aptitudeCalculator = new FeatureCalculator<VariableNumericFeature>() {
			
			@Override
			public Number calculate(VariableNumericFeature feature) {
				int result = feature.getCreationCost();
				result += (feature.getFreebieCost() / 10) + (feature.getExperienceCost() / 10) + (feature.getFreeCost() / 10);
				return result;
			}
		};
		List<VariableNumericFeature> aptitudes = new ArrayList<>(7);
		aptitudes.add(registerListener(new VariableNumericFeature("APT_COG", aptitudeCalculator)));
		aptitudes.add(registerListener(new VariableNumericFeature("APT_COO", aptitudeCalculator)));
		aptitudes.add(registerListener(new VariableNumericFeature("APT_INT", aptitudeCalculator)));
		aptitudes.add(registerListener(new VariableNumericFeature("APT_SOM", aptitudeCalculator)));
		aptitudes.add(registerListener(new VariableNumericFeature("APT_WIL", aptitudeCalculator)));
		aptitudes.add(registerListener(new VariableNumericFeature("APT_REF", aptitudeCalculator)));
		aptitudes.add(registerListener(new VariableNumericFeature("APT_SAV", aptitudeCalculator)));
		character.setAptitude(aptitudes);
		
		character.setMotivations(registerListener(new SimpleContainerFeature<StringFeature>("MOTIVATION", StringFeature.class)));
		// TODO Auto-generated method stub
		return character;
	}


}
