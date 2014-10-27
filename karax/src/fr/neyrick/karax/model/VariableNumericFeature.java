package fr.neyrick.karax.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.entities.generic.Increment;

@XmlTransient
public abstract class VariableNumericFeature extends AbstractSingleFeature {

	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private List<Increment> increments = new ArrayList<>();
		
	private FeatureCalculator<? extends VariableNumericFeature> calculator = null;
	
	private boolean uptodate = false;

	private Number value = 0;
	
	private Set<String> extraInfo = new HashSet<>();
	
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
	
	public FeatureCalculator<? extends VariableNumericFeature> getCalculator() {
		return calculator;
	}

	public void setCalculator(FeatureCalculator<? extends VariableNumericFeature> calculator) {
		this.calculator = calculator;
	}

	public int getCost(String amountKey) {
		int result = 0;
		for (Increment inc: increments) {
			if (amountKey.equals(inc.getAmountType())) result += inc.getAmount();
		}
		return result;
	}
	
	public int getRoll() {
		return getCost(Increment.ROLL);
	}
	
	public int getCreationCost() {
		return getCost(Increment.CREATION);
	}
	
	public int getExperienceCost() {
		return getCost(Increment.EXPERIENCE);
	}
	
	public int getFreeCost() {
		return getCost(Increment.FREE);
	}
	
	public int getFreebieCost() {
		return getCost(Increment.FREEBIE);
	}
	
	public int getModifier() {
		return getCost(Increment.MODIFIER);
	}
	
	public List<Increment> getIncrements() {
		return increments;
	}

	public int getRegularCost() {
		int result = 0;
		for (Increment inc: increments) {
			if (Increment.REGULAR_COSTS.contains(inc.getAmountType())) {
				 result += inc.getAmount();
			}
		}
		return result;
	}
	
	public int getTotalCost() {
		int result = 0;
		for (Increment inc: increments) {
			if (!Increment.MODIFIER.equals(inc.getAmountType())) result += inc.getAmount();
		}
		return result;
	}
	
	public VariableNumericFeature(FeaturesCollection container, String key, FeatureCalculator<? extends VariableNumericFeature> calculator) {
		super(container, key);
		setCalculator(calculator);
	}

	public VariableNumericFeature(String key, FeatureCalculator<? extends VariableNumericFeature> calculator) {
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
		String amountType = edit.getIncrement().getAmountType();
		if (amountType != null) {
			increments.add(edit.getIncrement());
			
			String extra = edit.getValue();
			if (extra != null) {
				if (extra.startsWith("-")) {
					extraInfo.remove(extra.substring(1));
				}
				else {
					extraInfo.add(extra);
				}
			}
			
			uptodate = false;
		}
	}

	public Set<String> getExtraInfo() {
		return extraInfo;
	}
	

}
