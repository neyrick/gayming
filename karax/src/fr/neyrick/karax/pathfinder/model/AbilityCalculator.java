package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;

public class AbilityCalculator extends AbstractNumericFeatureCalculator<Ability>{

	private AbilityCapper capper = null;
	
	private int getValueFromCost(int cost) {
		switch(cost) {
			case -4: return 7;
			case -2: return 8;
			case -1: return 9;
			case 0: return 10;
			case 1: return 11;
			case 2: return 12;
			case 3: return 13;
			case 5: return 14;
			case 7: return 15;
			case 10: return 16;
			case 13: return 17;
			case 17: return 18;
			default: throw new IllegalArgumentException("Invalid ability cost: " + cost); 
		}
	}
	
	@Override
	public Number calculateFeature(Ability feature) {
		int result = 0;
		
		result = feature.getRoll();
		if (result == 0) {
			result = getValueFromCost(feature.getCreationCost());
		}
		
		result += feature.getCost("RACE");
		result += feature.getExperienceCost();
		result += feature.getCost("MAGIC");

		int bonus = (result / 2) - 5;
		if ((capper != null) && (capper.getCap() < bonus)) {
			bonus = capper.getCap();
		}
		feature.setBonus(bonus);
		
		return result;
	}

	public AbilityCalculator(AbilityCapper capper) {
		super();
		this.capper = capper;
	}

	public AbilityCalculator() {
		super();
	}

}
