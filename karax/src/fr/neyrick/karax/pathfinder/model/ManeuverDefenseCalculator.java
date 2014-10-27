package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.SimpleVariable;

public class ManeuverDefenseCalculator extends AbstractNumericFeatureCalculator<SimpleVariable>{

	private Ability strength;
	private Ability dexterity;
	private FixedNumericFeature size;
	private SimpleVariable bab;
	
	@Override
	public Number calculateFeature(SimpleVariable feature) {

		int result = 10;
		
		
		result += bab.getNumericValue().intValue();
		result += strength.getBonus();
		result += dexterity.getBonus();
		result += size.getAmount();
				
		return result;
	}

	public ManeuverDefenseCalculator(SimpleVariable bab, Ability strength, Ability dexterity, FixedNumericFeature size) {
		super();
		this.strength = strength;
		this.dexterity = dexterity;
		this.bab = bab;
		this.size = size;
	}

}
