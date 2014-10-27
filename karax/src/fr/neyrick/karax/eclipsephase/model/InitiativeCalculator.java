package fr.neyrick.karax.eclipsephase.model;

import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.VariableNumericFeature;

public class InitiativeCalculator extends FeatureCalculator<SimpleVariable> {

	private VariableNumericFeature aptitudeINTFeature;
	
	private VariableNumericFeature aptitudeREFFeature;
	
	@Override
	public Number calculateFeature(SimpleVariable feature) {
		int base = (int)Math.ceil((aptitudeINTFeature.getNumericValue().intValue()+aptitudeREFFeature.getNumericValue().intValue())/5.);
		base += feature.getModifier();
		return base;
	}

	public InitiativeCalculator(VariableNumericFeature aptitudeINTFeature,
			VariableNumericFeature aptitudeREFFeature) {
		super();
		this.aptitudeINTFeature = aptitudeINTFeature;
		this.aptitudeREFFeature = aptitudeREFFeature;
	}

}
