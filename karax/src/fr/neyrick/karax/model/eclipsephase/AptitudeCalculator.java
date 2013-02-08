package fr.neyrick.karax.model.eclipsephase;

import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;

public class AptitudeCalculator implements FeatureCalculator{

	@Override
	public Number calculate(CharacterFeature feature) {
		Aptitude targetFeature = (Aptitude)feature;
		int result = targetFeature.getCreationCost();
		result += (targetFeature.getFreebieCost() / 10) + (targetFeature.getExperienceCost() / 10) + (targetFeature.getFreeCost() / 10);
		int max = targetFeature.getEgoMax();
		if (result > max) result = max;
		targetFeature.setBase(result);
		result += targetFeature.getMorphBonus();
		max = targetFeature.getMorphMax();
		if (result > max) result = max; 
		return result;
	}

}
