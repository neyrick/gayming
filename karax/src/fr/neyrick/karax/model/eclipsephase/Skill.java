package fr.neyrick.karax.model.eclipsephase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Skill extends VariableNumericFeature {

	private String linkedAptitude;
	
	private int base;
	
	@Override
	protected Number calculate() {
		base = getCalculator().calculate(this).intValue();
		return (base + getMorphBonus());
	}

	@XmlAttribute
	public String getLinkedAptitude() {
		return linkedAptitude;
	}

	@XmlAttribute
	public int getBase() {
		refresh();
		return base;
	}

	@XmlAttribute
	public int getMorphBonus() {
		return getAmount("MORPH");
	}

	@XmlAttribute
	@Override
	public String getKey() {
		return super.getKey();
	}

	@Override
	@XmlValue
	public String getValue() {
		return super.getValue();
	}

	public Skill() {
		this(null, null, null);
	}

	public Skill(FeaturesCollection container, String linkedAptitude, String key,
			SkillCalculator calculator) {
		super(container, key, calculator);
		this.linkedAptitude = linkedAptitude;
	}

	public Skill(String key, String linkedAptitude, SkillCalculator calculator) {
		super(key, calculator);
		this.linkedAptitude = linkedAptitude;
	}

	
}
