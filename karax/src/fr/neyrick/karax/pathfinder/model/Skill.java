package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Skill extends VariableNumericFeature {

	private static final String CUSTOM_SKILL_SEPARATOR = ":";
	private static final String CUSTOM_SKILL_PREFIX = "CS_";

	private String linkedAbility;
	
	private boolean nodefault = false;
	
	private boolean classskill = false;
	
	private boolean armorpenalty = false;
	
	private int ranks = 0;

	private int miscbonus = 0;

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
		return getCalculator().calculate(this).intValue();
	}

	@XmlAttribute
	public String getLinkedAbility() {
		return tryTranslation(linkedAbility);
	}

	public void setLinkedAbility(String linkedAbility) {
		this.linkedAbility = linkedAbility;
	}

	@XmlAttribute
	public int getRanks() {
		return ranks;
	}

	public void setRanks(int ranks) {
		this.ranks = ranks;
	}

	@XmlAttribute
	public int getMiscBonus() {
		return miscbonus;
	}

	public void setMiscbonus(int miscbonus) {
		this.miscbonus = miscbonus;
	}

	@XmlAttribute
	@Override
	public String getKey() {
		return super.getKey();
	}

	@XmlAttribute
	public boolean isNodefault() {
		return nodefault;
	}
	
	@XmlAttribute
	public boolean isClassskill() {
		return classskill;
	}

	@XmlAttribute
	public boolean isArmorpenalty() {
		return armorpenalty;
	}

	public void setClassskill(boolean classskill) {
		this.classskill = classskill;
	}

	public Skill() {
		this(null, null, null, false, false);
	}

	public Skill(String key, String linkedAbility, SkillCalculator calculator, boolean isNoDefault, boolean armorpenalty) {
		super(key, calculator);
		this.linkedAbility = linkedAbility;
		this.nodefault = isNoDefault;
		this.armorpenalty = armorpenalty;
	}

	
}
