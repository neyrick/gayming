package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SimpleVariable extends VariableNumericFeature {

	@Override
	@XmlAttribute
	public String getKey() {
		return super.getKey();
	}

	@Override
	@XmlValue
	public String getValue() {
		return super.getValue();
	}

	public SimpleVariable(FeaturesCollection container, String key,
			FeatureCalculator calculator) {
		super(container, key, calculator);
	}

	public SimpleVariable(String key, FeatureCalculator calculator) {
		super(key, calculator);
	}

	public SimpleVariable() {
		this(null,null);
	}
}
