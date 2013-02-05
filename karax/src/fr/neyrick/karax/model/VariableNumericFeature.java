package fr.neyrick.karax.model;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class VariableNumericFeature extends AbstractSingleFeature {

	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private Map<String, Integer> amounts = new HashMap<>();
		
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
		for (Integer value : amounts.values()) {
			result += value;
		}
		return result;
	}
	
	public VariableNumericFeature(ContainerFeature container, String key, FeatureCalculator calculator) {
		super(container, key);
		setCalculator(calculator);
	}

	public VariableNumericFeature(String key, FeatureCalculator calculator) {
		super(null, key);
		setCalculator(calculator);
	}

	public VariableNumericFeature() {
		super(null);
	}
	
	@XmlAttribute
	@Override
	public String getKey() {
		return super.getKey();
	}

	@XmlValue
	@Override
	public String getValue() {
		return format.format(getNumericValue());
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		String amountType = edit.getAmountType();
		if (amountType != null) {
			Integer currentValue = amounts.get(amountType);
			if (currentValue == null) amounts.put(amountType, edit.getAmount());
			else amounts.put(amountType, edit.getAmount() + currentValue);
		}
	}
	

}
