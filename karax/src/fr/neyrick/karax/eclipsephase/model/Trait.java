package fr.neyrick.karax.eclipsephase.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.FixedNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Trait extends FixedNumericFeature {

	private String level;
	
	private String descriptor;
	
	@XmlAttribute
	public String getDescriptor() {
		if (descriptor == null) return null;
		return tryTranslation(descriptor);
	}

	@XmlAttribute
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public Trait() {
		this(null);
	}

	public Trait(FeaturesCollection container, String key) {
		super(container, key);
	}

	public Trait(String key) {
		super(key);
	}

	@Override
	public void recordEdit(CharacterEdit edit) {
		super.recordEdit(edit);
		this.level = edit.getTargetSubKey2();
		this.descriptor = edit.getTargetSubKey3();
	}

	
}
