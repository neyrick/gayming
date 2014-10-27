package fr.neyrick.karax.eclipsephase.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;

public class AptitudeCalculator extends AbstractNumericFeatureCalculator<Aptitude> {

	@Override
	public Number calculateFeature(Aptitude feature) {
		int base = super.calculateFromRegularCost(feature);
		int max = feature.getEgoMax();
		if (base > max) base = max;
		int result = base;
		feature.setBase(result);
		result += feature.getMorphBonus();
		max = feature.getMorphMax();
		if (result > max) result = max; 
		feature.setEffectiveMorphBonus(result - base);
		return result;
	}

}
