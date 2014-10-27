package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.VariableFeaturesCollection;

public class HitPointCalculator extends AbstractNumericFeatureCalculator<SimpleVariable>{

	private Ability constitution;
	
	private VariableFeaturesCollection<SimpleVariable> levels;
	
	@Override
	public Number calculateFeature(SimpleVariable feature) {
		
		int result = calculateFromTotalCost(feature);
		
		result += levels.getTotalIntValue() * constitution.getBonusValue();
				
		return result;
	}

	public HitPointCalculator(Ability constitution, VariableFeaturesCollection<SimpleVariable> levels) {
		super();
		this.constitution = constitution;
		this.levels = levels;
	}

}
