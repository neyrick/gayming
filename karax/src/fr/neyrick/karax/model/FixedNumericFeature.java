package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
public class FixedNumericFeature extends AbstractSingleFeature {

//	private static final NumberFormat format = NumberFormat.getNumberInstance();
	
	private int amount;	
	
	private String value;
	
	@Override
	@XmlValue
	public String getValue() {
		return value;
	}

	@XmlAttribute
	public int getAmount() {
		return amount;
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
		this.amount = edit.getAmount();
		this.value = edit.getValue();
	}
	
	
}
