package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
public class FixedNumericFeature extends AbstractFeature {

//	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private int cost;	
	
	private String value;
	
	@Override
	@XmlValue
	public String getValue() {
		return value;
	}

	@XmlAttribute
	public int getCost() {
		return cost;
	}

	public FixedNumericFeature(String key) {
		super(key);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		this.cost = edit.getSpentAmount();
		this.value = edit.getValue();
	}
	
	
}
