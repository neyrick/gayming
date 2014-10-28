package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;

public class SkillCalculator extends AbstractNumericFeatureCalculator<Skill>{

	private Ability baseAbility;
	private Load load;
	
	@Override
	public Number calculateFeature(Skill feature) {
		int result = feature.getExperienceCost();
		
		feature.setRanks(result);
		
		int miscBonus = 0;
		
		if (result > 0) {
			if (feature.isClassskill()) {
				miscBonus += 3;
			}
		}
		else if (feature.isNodefault()) {
			return 0;
		}
		
		int abilityBonus = baseAbility.getBonus();		
		result += baseAbility.getBonus();
		feature.setAbilityBonus(abilityBonus);
		
		if (feature.isUseArmorpenalty()) {
			result += load.getPenalty();
			feature.setArmorPenalty(load.getPenalty());
		}
		
		miscBonus += feature.getModifier();
		feature.setMiscbonus(miscBonus);
		result += miscBonus;
		
		return result;
	}

	public SkillCalculator(Ability baseAbility, Load load) {
		super();
		this.baseAbility = baseAbility;
		this.load = load;
	}

}
