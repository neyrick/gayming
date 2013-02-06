package fr.neyrick.karax.model.eclipsephase;

import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.VariableNumericFeature;

public class InitiativeCalculator implements FeatureCalculator {

	private VariableNumericFeature aptitudeINTFeature;
	
	private VariableNumericFeature aptitudeREFFeature;
	
	@Override
	public Number calculate(CharacterFeature feature) {
		int base = (int)Math.ceil((aptitudeINTFeature.getNumericValue().intValue()+aptitudeREFFeature.getNumericValue().intValue())/5.);
		base += ((VariableNumericFeature)feature).getModifier();
		return base;
	}

	public InitiativeCalculator(VariableNumericFeature aptitudeINTFeature,
			VariableNumericFeature aptitudeREFFeature) {
		super();
		this.aptitudeINTFeature = aptitudeINTFeature;
		this.aptitudeREFFeature = aptitudeREFFeature;
	}

}
