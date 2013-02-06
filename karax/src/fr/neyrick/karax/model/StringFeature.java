package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class StringFeature extends AbstractSingleFeature {

	private String value;	
	
	@Override
	@XmlValue
	public String getValue() {
		if (value == null) return "";
		return value;
	}

	public StringFeature(FeaturesCollection parent, String key) {
		super(parent, key);
	}

	public StringFeature(String key) {
		super(null, key);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		this.value = edit.getValue();
	}
	
	public StringFeature() {
		super(null);
	}
	
}
