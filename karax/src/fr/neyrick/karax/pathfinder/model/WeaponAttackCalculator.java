package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.SimpleBonus;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StringFeature;

public class WeaponAttackCalculator extends AbstractNumericFeatureCalculator<SimpleBonus>{

	private Map<String, Ability> abilitiesMap;
	private SimpleVariable baseAttackBonus;
	private StringFeature abilityKey;
	
	@Override
	public Number calculateFeature(SimpleBonus feature) {

		int result = calculateFromTotalCost(feature);
		
		result += baseAttackBonus.getNumericValue().intValue();
		
		String abilityKeyValue = abilityKey.getValue();		
		if (abilityKeyValue != null) {
			Ability ability = abilitiesMap.get(abilityKeyValue);
			result += ability.getBonus();
			
		}
				
		return result;
	}

	public WeaponAttackCalculator(Map<String, Ability> abilitiesMap,
			SimpleVariable baseAttackBonus, StringFeature abilityKey) {
		super();
		this.abilitiesMap = abilitiesMap;
		this.baseAttackBonus = baseAttackBonus;
		this.abilityKey = abilityKey;
	}

}
