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

	private static final String CUSTOM_SKILL_SEPARATOR = ":";
	private static final String CUSTOM_SKILL_PREFIX = "CS_";
	
	private String linkedAptitude;
	
	private int base;
	
	private boolean nodefault = false;
	
	private int aptitudeMorphBonus = 0;
	
	
	public int getAptitudeMorphBonus() {
		return aptitudeMorphBonus;
	}

	public void setAptitudeMorphBonus(int aptitudeMorphBonus) {
		this.aptitudeMorphBonus = aptitudeMorphBonus;
	}

	@XmlAttribute
	private String category;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@XmlAttribute
	public String getDisplay() {
		String key = getKey();
		int index = key.indexOf(CUSTOM_SKILL_SEPARATOR);
		if (index > -1) {
			return tryTranslation(CUSTOM_SKILL_PREFIX + key.substring(0,  index)) + key.substring(index);
		}
		else {
			return tryTranslation(getKey());
		}
	}

	@Override
	protected Number calculate() {
		base = getCalculator().calculate(this).intValue();
		return (base + getMorphBonus());
	}

	@XmlAttribute
	public String getLinkedAptitude() {
		return tryTranslation(linkedAptitude);
	}

	public void setLinkedAptitude(String linkedAptitude) {
		this.linkedAptitude = linkedAptitude;
	}

	@XmlAttribute
	public int getBase() {
		refresh();
		return base;
	}

	@XmlAttribute
	public int getMorphBonus() {
		return aptitudeMorphBonus + getAmount("MORPH");
	}

	public int getDiscountCost() {
		return getAmount("DISCOUNT");
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
		this(null, null, null, false, null);
	}

	public Skill(FeaturesCollection container, String linkedAptitude, String key,
			SkillCalculator calculator, boolean isNoDefault, String category) {
		super(container, key, calculator);
		this.linkedAptitude = linkedAptitude;
		this.nodefault = isNoDefault;
		this.category = category;
	}

	public Skill(String key, String linkedAptitude, SkillCalculator calculator, boolean isNoDefault, String category) {
		super(key, calculator);
		this.linkedAptitude = linkedAptitude;
		this.nodefault = isNoDefault;
		this.category = category;
	}

	
}
