package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class FixedBonus extends FixedNumericFeature {

	private static final BonusFormat bonusFormat = new BonusFormat();

	
	@Override
	public String getValue() {
		return bonusFormat.format(super.getAmount());
	}

	public FixedBonus(FeaturesCollection container, String key) {
		super(container, key);
	}

	public FixedBonus(String key) {
		super(key);
	}

	public FixedBonus() {
	}

}
