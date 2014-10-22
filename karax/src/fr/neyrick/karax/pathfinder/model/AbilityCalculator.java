package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.VariableNumericFeature;

public class AbilityCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private int getValueFromCost(int cost) {
		switch(cost) {
			case -4: return 7;
			case -2: return 8;
			case -1: return 9;
			case 0: return 10;
			case 1: return 11;
			case 2: return 12;
			case 3: return 13;
			case 5: return 14;
			case 7: return 15;
			case 10: return 16;
			case 13: return 17;
			case 17: return 18;
			default: throw new IllegalArgumentException("Invalid ability cost: " + cost); 
		}
	}
	
	@Override
	public Number calculate(CharacterFeature feature) {
		VariableNumericFeature targetFeature = (VariableNumericFeature)feature;
		
		int result = 0;
		
		result = targetFeature.getRoll();
		if (result == 0) {
			result = getValueFromCost(targetFeature.getCreationCost());
		}
		
		result += targetFeature.getCost("RACE");
		result += targetFeature.getExperienceCost();
		result += targetFeature.getCost("MAGIC");

		return result;
	}

}
