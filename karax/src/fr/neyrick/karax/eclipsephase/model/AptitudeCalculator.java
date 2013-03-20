package fr.neyrick.karax.eclipsephase.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;

public class AptitudeCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	@Override
	public Number calculate(CharacterFeature feature) {
		Aptitude targetFeature = (Aptitude)feature;
		int base = super.calculateFromRegularCost(targetFeature);
		int max = targetFeature.getEgoMax();
		if (base > max) base = max;
		int result = base;
		targetFeature.setBase(result);
		result += targetFeature.getMorphBonus();
		max = targetFeature.getMorphMax();
		if (result > max) result = max; 
		targetFeature.setEffectiveMorphBonus(result - base);
		return result;
	}

}
