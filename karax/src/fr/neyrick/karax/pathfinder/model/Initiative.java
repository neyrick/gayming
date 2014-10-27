package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.model.BonusFormat;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Initiative extends VariableNumericFeature {

	private static final BonusFormat signFormat = new BonusFormat();
	
	private int miscBonus = 0;
	
	@XmlAttribute
	public String getMisc() {
		refresh();
		return signFormat.format(miscBonus);
	}

	@Override
	@XmlValue
	public String getValue() {
		return signFormat.format(super.getNumericValue());
	}
	
	public void setMisc(int miscBonus) {
		this.miscBonus = miscBonus;
	}

	public Initiative() {
		this(null, null);
	}

	public Initiative(FeaturesCollection container, String key,
			InitiativeCalculator calculator) {
		super(container, key, calculator);
	}

	public Initiative(String key, InitiativeCalculator calculator) {
		super(key, calculator);
	}

	
}
