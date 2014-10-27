package fr.neyrick.karax.eclipsephase.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;

public class SkillCalculator extends AbstractNumericFeatureCalculator<Skill>{

	private Aptitude baseAptitude;
	
	@Override
	public Number calculateFeature(Skill feature) {
		int spent = feature.getRegularCost();
		if ((spent == 0) && feature.isNodefault()) {
			return 0;
		}
		int result = baseAptitude.getBase();
		feature.setAptitudeMorphBonus(baseAptitude.getEffectiveMorphBonus());
		result += super.calculateFromRegularCost(feature);
		if (result > 60) result -= Math.ceil((result - 60) / 2.);
		result += feature.getModifier();
		if (result < 0) return 0;
		return result;
	}

	public SkillCalculator(Aptitude baseAptitude) {
		super();
		this.baseAptitude = baseAptitude;
	}

}
