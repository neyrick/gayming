package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;

public class InitiativeCalculator extends AbstractNumericFeatureCalculator<Initiative>{

	private Ability dexterity;
	
	@Override
	public Number calculateFeature(Initiative feature) {

		int result = calculateFromTotalCost(feature);
		feature.setMisc(result);
		
		
		result += dexterity.getBonus();
				
		return result;
	}

	public InitiativeCalculator(Ability dexterity) {
		super();
		this.dexterity = dexterity;
	}

}
