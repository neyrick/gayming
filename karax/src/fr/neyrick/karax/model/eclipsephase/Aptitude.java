package fr.neyrick.karax.model.eclipsephase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Aptitude extends VariableNumericFeature {

	private int base;
	
	private int maxAptitude;
	
	@Override
	protected Number calculate() {
		base = getCalculator().calculate(this).intValue();
		int total = base + getMorphBonus();
		if (total > maxAptitude) total = maxAptitude;
		return total;
	}

	@XmlAttribute
	public String getDisplay() {
		return tryTranslation(getKey());
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

	public Aptitude() {
		this(null, 0, null);
	}

	public Aptitude(FeaturesCollection container, int maxAptitude, String key,
			FeatureCalculator calculator) {
		super(container, key, calculator);
		this.maxAptitude = maxAptitude;
	}

	public Aptitude(String key, int maxAptitude, FeatureCalculator calculator) {
		super(key, calculator);
		this.maxAptitude = maxAptitude;
	}

	
}
