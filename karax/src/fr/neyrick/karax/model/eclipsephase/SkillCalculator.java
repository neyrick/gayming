package fr.neyrick.karax.model.eclipsephase;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;

public class SkillCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private Aptitude baseAptitude;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		Skill targetFeature = (Skill)feature;
		int spent = targetFeature.getRegularCost();
		if ((spent == 0) && targetFeature.isNodefault()) {
			return 0;
		}
		int result = baseAptitude.getBase();
		targetFeature.setAptitudeMorphBonus(baseAptitude.getEffectiveMorphBonus());
		result += super.calculateFromRegularCost(targetFeature);
		if (result > 60) result -= Math.ceil((result - 60) / 2.);
		result += targetFeature.getModifier();
		if (result < 0) return 0;
		return result;
	}

	public SkillCalculator(Aptitude baseAptitude) {
		super();
		this.baseAptitude = baseAptitude;
	}

}
