package fr.neyrick.karax.model.eclipsephase;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Skill extends VariableNumericFeature {

	private String linkedAptitude;
	
	private int base;
	
	private boolean nodefault = false;
	
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

	@XmlAttribute
	public String getTotal() {
		return super.getValue();
	}

	@XmlAttribute
	public boolean isNodefault() {
		return nodefault;
	}

	@XmlElement(name="extra")
	@Override
	public Set<String> getExtraInfo() {
		return super.getExtraInfo();
	}

	public Skill() {
		this(null, null, null, false);
	}

	public Skill(FeaturesCollection container, String linkedAptitude, String key,
			SkillCalculator calculator, boolean isNoDefault) {
		super(container, key, calculator);
		this.linkedAptitude = linkedAptitude;
		this.nodefault = isNoDefault;
	}

	public Skill(String key, String linkedAptitude, SkillCalculator calculator, boolean isNoDefault) {
		super(key, calculator);
		this.linkedAptitude = linkedAptitude;
		this.nodefault = isNoDefault;
	}

	
}
