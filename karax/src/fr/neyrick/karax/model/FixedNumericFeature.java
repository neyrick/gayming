package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
public class FixedNumericFeature extends AbstractSingleFeature {

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

	public FixedNumericFeature(ContainerFeature container, String key) {
		super(container, key);
	}

	public FixedNumericFeature(String key) {
		super(key);
	}

	public FixedNumericFeature() {
		super(null);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		this.cost = edit.getSpentAmount();
		this.value = edit.getValue();
	}
	
	
}
