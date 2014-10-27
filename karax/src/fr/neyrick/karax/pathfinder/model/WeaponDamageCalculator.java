package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.SimpleBonus;
import fr.neyrick.karax.model.StringFeature;

public class WeaponDamageCalculator extends AbstractNumericFeatureCalculator<SimpleBonus>{

	private Map<String, Ability> abilitiesMap;
	private StringFeature abilityKey;
	
	@Override
	public Number calculateFeature(SimpleBonus feature) {

		int result = calculateFromTotalCost(feature);
		
		String abilityKeyValue = abilityKey.getValue();		
		if ((abilityKeyValue != null) && (!"".equals(abilityKeyValue))) {
			Ability ability = abilitiesMap.get(abilityKeyValue);
			result += ability.getBonus();
			
		}
				
		return result;
	}

	public WeaponDamageCalculator(Map<String, Ability> abilitiesMap,StringFeature abilityKey) {
		super();
		this.abilitiesMap = abilitiesMap;
		this.abilityKey = abilityKey;
	}

}
