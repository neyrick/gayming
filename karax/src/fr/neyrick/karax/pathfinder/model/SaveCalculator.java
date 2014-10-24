package fr.neyrick.karax.pathfinder.model;

import fr.neyrick.karax.model.AbstractNumericFeatureCalculator;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;

public class SaveCalculator extends AbstractNumericFeatureCalculator implements FeatureCalculator{

	private Ability ability;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		SimpleVariable save = (SimpleVariable)feature;
		
		int result = save.getTotalCost();
		
		result += ability.getBonusValue();
		
		return result;
	}

	public SaveCalculator(Ability ability) {
		super();
		this.ability = ability;
	}

}
