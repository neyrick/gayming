package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.VariableNumericFeature;

public class InitiativeCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private Ability dexterity;
	
	@Override
	public Number calculate(CharacterFeature feature) {

		int result = calculateFromTotalCost((VariableNumericFeature) feature);
		
		result += dexterity.getBonusValue();
				
		return result;
	}

	public InitiativeCalculator(Ability dexterity) {
		super();
		this.dexterity = dexterity;
	}

}
