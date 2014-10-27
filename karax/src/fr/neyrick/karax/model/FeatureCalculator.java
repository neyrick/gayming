package fr.neyrick.karax.model;

public abstract class FeatureCalculator<T extends CharacterFeature> {

	public abstract Number calculateFeature(T feature);
	
	@SuppressWarnings("unchecked")
	public Number calculate(VariableNumericFeature feature) {
		return calculateFeature((T)feature);
	}
	
	public static <F extends VariableNumericFeature> FeatureCalculator<F> getDefaultInstance(Class<F> featureClass) {
		return new AbstractNumericFeatureCalculator<F>() {

			@Override
			public Number calculateFeature(F feature) {
				return super.calculateFromTotalCost(feature);
			}
		};
	}
}
