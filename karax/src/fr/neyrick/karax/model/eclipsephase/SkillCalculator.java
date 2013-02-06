package fr.neyrick.karax.model.eclipsephase;

import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.VariableNumericFeature;

public class SkillCalculator implements FeatureCalculator{

	private VariableNumericFeature aptitudeFeature;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		VariableNumericFeature targetFeature = (VariableNumericFeature)feature;
		int result = aptitudeFeature.getNumericValue().intValue();
		result += targetFeature.getTotalCost();
		if (result > 60) result -= (result - 60) / 2;
		return result;
	}

	public SkillCalculator(VariableNumericFeature baseFeature) {
		super();
		this.aptitudeFeature = baseFeature;
	}

}
