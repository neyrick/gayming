package fr.neyrick.karax.model;

import java.text.NumberFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.entities.generic.CharacterEdit.ExpenseType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class VariableNumericFeature extends AbstractFeature {

	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private int creationCost = 0;	
	private int freeCost = 0;	
	private int freebieCost = 0;	
	private int experienceCost = 0;	
	
	private FeatureCalculator<VariableNumericFeature> calculator;
	
	public int getCreationCost() {
		return creationCost;
	}

	public int getFreeCost() {
		return freeCost;
	}
	
	public int getFreebieCost() {
		return freebieCost;
	}

	public int getExperienceCost() {
		return experienceCost;
	}

	public int getTotalCost() {
		return creationCost + freeCost + freebieCost + experienceCost;
	}
	
	public VariableNumericFeature(String key, FeatureCalculator<VariableNumericFeature> calculator) {
		super(key);
		this.calculator = calculator;
	}

	private VariableNumericFeature() {
		super(null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	@XmlAttribute
	@Override
	public String getKey() {
		return super.getKey();
	}

	@XmlValue
	@Override
	public String getValue() {
		return format.format(calculator.calculate(this));
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		if (ExpenseType.CREATION.equals(edit.getExpenseType())) creationCost += edit.getSpentAmount();
		else if (ExpenseType.FREE.equals(edit.getExpenseType())) freeCost += edit.getSpentAmount();
		else if (ExpenseType.FREEBIE.equals(edit.getExpenseType())) freebieCost += edit.getSpentAmount();
		else if (ExpenseType.EXPERIENCE.equals(edit.getExpenseType())) experienceCost += edit.getSpentAmount();
	}
	

}
