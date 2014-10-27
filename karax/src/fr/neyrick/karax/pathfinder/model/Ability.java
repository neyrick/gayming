package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.model.BonusFormat;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Ability extends VariableNumericFeature {

	private static final BonusFormat signFormat = new BonusFormat();

	@XmlAttribute
	public String getDisplay() {
		return tryTranslation(getKey());
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

	@XmlAttribute
	public int getBonus() {
		return (this.getNumericValue().intValue() / 2)-5;
	}

	@XmlAttribute
	public String getSignedBonus() {
		return signFormat.format((this.getNumericValue().intValue() / 2)-5);
	}

	public Ability() {
		this(null, null);
	}

	public Ability(FeaturesCollection container, String key,
			FeatureCalculator<Ability> calculator) {
		super(container, key, calculator);
	}

	public Ability(String key, FeatureCalculator<Ability> calculator) {
		super(key, calculator);
	}

	
}
