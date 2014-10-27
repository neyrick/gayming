package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;

public class SkillCalculator extends AbstractNumericFeatureCalculator<Skill>{

	private Ability baseAbility;
	private SimpleVariable armorpenalty;
	
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
		
		if (feature.isArmorpenalty()) {
			result += this.armorpenalty.getNumericValue().intValue();
		}
		
		miscBonus += feature.getModifier();
		feature.setMiscbonus(miscBonus);
		result += miscBonus;
		
		return result;
	}

	public SkillCalculator(Ability baseAbility, SimpleVariable armorpenalty) {
		super();
		this.baseAbility = baseAbility;
		this.armorpenalty = armorpenalty;
	}

}
