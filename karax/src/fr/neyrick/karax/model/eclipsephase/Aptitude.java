package fr.neyrick.karax.model.eclipsephase;

import fr.neyrick.karax.model.VariableNumericFeature;

public class Aptitude extends VariableNumericFeature {

	public Aptitude(String key) {
		super(key);
	}

	@Override
	public String getValue() {
		return Integer.toString(getTotalCost() / 10);
	}
	
	
}
