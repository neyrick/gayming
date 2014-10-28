package fr.neyrick.karax.pathfinder.model;

import java.util.Map;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StringFeature;

public class SpellSlotsCalculator extends AbstractNumericFeatureCalculator<SimpleVariable>{

	private StringFeature abilityKey;
	
	private Map<String, Ability> abilitiesMap;
	
	@Override
	public Number calculateFeature(SimpleVariable feature) {
						
		int result = calculateFromTotalCost(feature);
		int level = Integer.parseInt(feature.getKey());
		int bonus = abilitiesMap.get(abilityKey.getValue()).getBonus();
		
		int bonusslots = 0;
		if ((level > 0) && (bonus >= level)) {
			bonusslots = 1 + (bonus - level)/4;
		}
		
		return result + bonusslots;
	}

	public SpellSlotsCalculator(StringFeature abilityKey, Map<String, Ability> abilitiesMap) {
		super();
		this.abilityKey = abilityKey;
		this.abilitiesMap = abilitiesMap;
	}

}
