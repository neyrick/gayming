package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;

public class SaveCalculator extends AbstractNumericFeatureCalculator<Save> {

	private Ability ability;
	
	@Override
	public Number calculateFeature(Save feature) {
		int result = feature.getTotalCost();
		
		int magicBonus = feature.getCost("MAGIC");
		int baseBonus = feature.getCost("EXPERIENCE");
		int miscBonus = result - magicBonus - baseBonus;		
		int abilityBonus = ability.getBonusValue();
		
		feature.setBaseBonus(baseBonus);
		feature.setMagicBonus(magicBonus);
		feature.setAbilityBonus(abilityBonus);
		feature.setMisc(miscBonus);		

		return result + abilityBonus;
	}

	public SaveCalculator(Ability ability) {
		super();
		this.ability = ability;
	}

}
