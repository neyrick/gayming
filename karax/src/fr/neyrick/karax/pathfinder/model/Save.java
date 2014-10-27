package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Save extends VariableNumericFeature {

	private static final BonusFormat signFormat = new BonusFormat();
	
	private int baseBonus = 0;
	private int abilityBonus = 0;
	private int magicBonus = 0;
	private int miscBonus = 0;
	
		
	@XmlAttribute
	@Override
	public String getKey() {
		return super.getKey();
	}

	@XmlAttribute
	public int getMisc() {
		refresh();
		return miscBonus;
	}

	@Override
	@XmlValue
	public String getValue() {
		return signFormat.format(super.getNumericValue());
	}
	
	public void setMisc(int miscBonus) {
		this.miscBonus = miscBonus;
	}

	@XmlAttribute
	public int getBaseBonus() {
		refresh();
		return baseBonus;
	}

	public void setBaseBonus(int baseBonus) {
		this.baseBonus = baseBonus;
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
	public String getMagicBonus() {
		refresh();
		return signFormat.format(magicBonus);
	}

	public void setMagicBonus(int magicBonus) {
		this.magicBonus = magicBonus;
	}

	public Save() {
		this(null, null);
	}

	public Save(FeaturesCollection container, String key,
			SaveCalculator calculator) {
		super(container, key, calculator);
	}

	public Save(String key, SaveCalculator calculator) {
		super(key, calculator);
	}

	
}
