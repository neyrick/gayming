package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlRootElement
public class StringFeature extends AbstractFeature {

	private String value;	
	
	@Override
	@XmlValue
	public String getValue() {
		return value;
	}

	public StringFeature(String key) {
		super(key);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		this.value = edit.getValue();
	}
	

}
