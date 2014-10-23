package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.VariableFeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

public class HitPointCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private Ability constitution;
	
	private VariableFeaturesCollection<SimpleVariable> levels;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		
		int result = calculateFromTotalCost((VariableNumericFeature) feature);
		
		result += levels.getTotalIntValue() * constitution.getBonus();
				
		return result;
	}

	public HitPointCalculator(Ability constitution, VariableFeaturesCollection<SimpleVariable> levels) {
		super();
		this.constitution = constitution;
		this.levels = levels;
	}

}
