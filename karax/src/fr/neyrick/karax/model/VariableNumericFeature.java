package fr.neyrick.karax.model;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public abstract class VariableNumericFeature extends AbstractSingleFeature {

	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private Map<String, Integer> amounts = new HashMap<>();
		
	private FeatureCalculator calculator = null;
	
	private boolean uptodate = false;

	private Number value = 0;
	
	protected Number calculate() {
		return calculator.calculate(this);
	}
	
	protected void refresh() {
		if (!uptodate) setValue(calculate());
		uptodate = true;
	}
	
	protected String format(Number value) {
		return format.format(value);
	}
	
	public Number getNumericValue() {
		refresh();
		return value;
	}
	
	public FeatureCalculator getCalculator() {
		return calculator;
	}

	public void setCalculator(FeatureCalculator calculator) {
		this.calculator = calculator;
	}

	public int getAmount(String amountKey) {
		Integer result = amounts.get(amountKey);
		return (result == null ? 0 : result.intValue());
	}
	
	public int getCreationCost() {
		return getAmount(CharacterEdit.CREATION);
	}
	
	public int getExperienceCost() {
		return getAmount(CharacterEdit.EXPERIENCE);
	}
	
	public int getFreeCost() {
		return getAmount(CharacterEdit.FREE);
	}
	
	public int getFreebieCost() {
		return getAmount(CharacterEdit.FREEBIE);
	}
	
	public int getModifier() {
		return getAmount(CharacterEdit.MODIFIER);
	}
	
	public int getTotalCost() {
		int result = 0;
		for (Map.Entry<String, Integer> entry : amounts.entrySet()) {
			if (!CharacterEdit.MODIFIER.equals(entry.getKey())) result += entry.getValue();
		}
		return result;
	}
	
	public VariableNumericFeature(FeaturesCollection container, String key, FeatureCalculator calculator) {
		super(container, key);
		setCalculator(calculator);
	}

	public VariableNumericFeature(String key, FeatureCalculator calculator) {
		super(null, key);
		setCalculator(calculator);
	}

	@Override
	public String getValue() {
		return format(getNumericValue());
	}

	protected void setValue(Number value) {
		this.value = value;
	}
	
	@Override
	public void recordEdit(CharacterEdit edit) {
		String amountType = edit.getAmountType();
		if (amountType != null) {
			Integer currentValue = amounts.get(amountType);
			if (currentValue == null) amounts.put(amountType, edit.getAmount());
			else amounts.put(amountType, edit.getAmount() + currentValue);
			uptodate = false;
		}
	}
	

}
