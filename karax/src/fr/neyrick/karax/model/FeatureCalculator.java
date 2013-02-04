package fr.neyrick.karax.model;

public interface FeatureCalculator<T extends CharacterFeature> {

	public Number calculate(T feature);
	
}
