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
	
	@XmlAttribute
	public String getDisplay() {
		return tryTranslation(getKey());
	}

	@XmlAttribute
	public int getBase() {
		refresh();
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	@XmlAttribute
	public int getMorphBonus() {
		return getAmount("MORPH");
	}

	public int getMorphMax() {
		return getAmount("MORPH_MAX");
	}
	
	public int getEgoMax() {
		return 30 + getAmount("EGO_MAX");
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
		this(null, null);
	}

	public Aptitude(FeaturesCollection container, String key,
			FeatureCalculator calculator) {
		super(container, key, calculator);
	}

	public Aptitude(String key, FeatureCalculator calculator) {
		super(key, calculator);
	}

	
}
