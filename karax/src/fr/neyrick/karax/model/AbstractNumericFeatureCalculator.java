package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.Increment;

public abstract class AbstractNumericFeatureCalculator<T extends VariableNumericFeature> extends FeatureCalculator<T> {

	protected int calculateFromRegularCost(T feature) {
		double result = 0.;
		for (Increment inc : feature.getIncrements()) {
			if (Increment.REGULAR_COSTS.contains(inc.getAmountType())) {
				result += inc.getAmount() * inc.getMultiplier();
			}
		}
		return (int)result;
	}
	
	protected int calculateFromTotalCost(T feature) {
		double result = 0.;
		for (Increment inc : feature.getIncrements()) {
			result += inc.getAmount() * inc.getMultiplier();
		}
		return (int)result;
	}
	
}
