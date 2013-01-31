package fr.neyrick.karax.model;

import java.io.Serializable;
import java.text.NumberFormat;

public class FixedNumericFeature extends AbstractFeature {

	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	@Override
	public String getStringValue() {		
		return format.format(getValue());
	}

	@Override
	public void setValue(Serializable value) {
		if (!(value instanceof Number)) 
			throw new IllegalArgumentException(value + " is not a Number");
		super.setValue(value);
	}

	
}
