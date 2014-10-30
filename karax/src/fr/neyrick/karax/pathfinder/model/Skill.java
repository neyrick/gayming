package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.model.BonusFormat;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Skill extends VariableNumericFeature {

	private static final BonusFormat signFormat = new BonusFormat();

	private static final String FAVORED_COST="FAVORED";
	private static final String CUSTOM_SKILL_SEPARATOR = ":";
	private static final String CUSTOM_SKILL_PREFIX = "CS_";

	private String linkedAbility;
	
	private boolean nodefault = false;
	
	private boolean useArmorPenalty = false;
	
	private int ranks = 0;

	private int miscbonus = 0;

	private int abilityBonus = 0;
	
	private int armorPenalty = 0;
	
	
	public int getFavoredCost() {
		return getCost(FAVORED_COST);
	}
		
	@Override
	@XmlValue
	public String getValue() {
		return signFormat.format(super.getNumericValue());
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

	@XmlAttribute
	public int getArmorPenalty() {
		return armorPenalty;
	}

	public void setArmorPenalty(int armorPenalty) {
		this.armorPenalty = armorPenalty;
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
		refresh();
		return ranks;
	}

	public void setRanks(int ranks) {
		this.ranks = ranks;
	}

	@XmlAttribute
	public int getMiscBonus() {
		refresh();
		return miscbonus;
	}

	public void setMiscbonus(int miscbonus) {
		this.miscbonus = miscbonus;
	}
	
	@XmlAttribute
	public int getAbilityBonus() {
		refresh();
		return abilityBonus;
	}

	public void setAbilityBonus(int abilityBonus) {
		this.abilityBonus = abilityBonus;
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
		return ((PathfinderSkillList)this.getContainer()).isClassSkill(getKey());
	}

	@XmlAttribute
	public boolean isUseArmorpenalty() {
		return useArmorPenalty;
	}

	public Skill() {
		this(null, null, null, false, false);
	}

	public Skill(String key, String linkedAbility, SkillCalculator calculator, boolean isNoDefault, boolean useArmorPenalty) {
		super(key, calculator);
		this.linkedAbility = linkedAbility;
		this.nodefault = isNoDefault;
		this.useArmorPenalty = useArmorPenalty;
	}

	
}
