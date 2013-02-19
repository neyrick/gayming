package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.Increment;

public abstract class AbstractNumericFeatureCalculator implements
		FeatureCalculator {

	protected int calculateFromRegularCost(VariableNumericFeature feature) {
		double result = 0.;
		for (Increment inc : feature.getIncrements()) {
			if (Increment.REGULAR_COSTS.contains(inc.getAmountType())) {
				result += inc.getAmount() * inc.getMultiplier();
			}
		}
		return (int)result;
	}
	
	protected int calculateFromTotalCost(VariableNumericFeature feature) {
		double result = 0.;
		for (Increment inc : feature.getIncrements()) {
			result += inc.getAmount() * inc.getMultiplier();
		}
		return (int)result;
	}
	
}
