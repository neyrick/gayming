package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.SimpleBonus;
import fr.neyrick.karax.model.SimpleVariable;

public class ManeuverAttackCalculator extends AbstractNumericFeatureCalculator<SimpleBonus>{

	private Ability strength;
	private FixedNumericFeature size;
	private SimpleVariable bab;
	
	@Override
	public Number calculateFeature(SimpleBonus feature) {

		int result = 0;
		
		
		result += bab.getNumericValue().intValue();
		result += strength.getBonus();
		result += size.getAmount();
				
		return result;
	}

	public ManeuverAttackCalculator(SimpleVariable bab, Ability strength, FixedNumericFeature size) {
		super();
		this.strength = strength;
		this.bab = bab;
		this.size = size;
	}

}
