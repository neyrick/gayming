package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAttribute;


public class SimpleBonus extends SimpleVariable {

	private static final BonusFormat bonusFormat = new BonusFormat();
	
	@XmlAttribute
	protected int getRaw() {
		return super.getNumericValue().intValue();
	}
	
	@Override
	protected String format(Number value) {
		return bonusFormat.format(value);
	}

	public SimpleBonus() {
		super();
	}

	public SimpleBonus(FeaturesCollection container, String key,
			FeatureCalculator<? extends SimpleBonus> calculator) {
		super(container, key, calculator);
	}

	public SimpleBonus(FeaturesCollection container, String key) {
		super(container, key);
	}

	public SimpleBonus(String key,
			FeatureCalculator<? extends SimpleBonus> calculator) {
		super(key, calculator);
	}

	public SimpleBonus(String key) {
		super(key);
	}

}
