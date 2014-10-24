package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;

public class SkillCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private Ability baseAbility;
	private SimpleVariable armorpenalty;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		Skill skill = (Skill)feature;
		
		int result = skill.getExperienceCost();
		
		skill.setRanks(result);
		
		if (result > 0) {
			if (skill.isClassskill()) {
				result += 3;
			}
		}
		else if (skill.isNodefault()) {
			return 0;
		}
		
		result += baseAbility.getBonusValue();
		
		if (skill.isArmorpenalty()) {
			result += this.armorpenalty.getNumericValue().intValue();
		}
		
		skill.setMiscbonus(skill.getModifier());
		result += skill.getMiscBonus();
		
		return result;
	}

	public SkillCalculator(Ability baseAbility, SimpleVariable armorpenalty) {
		super();
		this.baseAbility = baseAbility;
		this.armorpenalty = armorpenalty;
	}

}
