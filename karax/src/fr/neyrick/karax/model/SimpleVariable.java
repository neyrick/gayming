package fr.neyrick.karax.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SimpleVariable extends VariableNumericFeature {

	
	@Override
	protected Number calculate() {
			return getCalculator().calculate(this);
	}
		
	@Override
	@XmlAttribute
	public String getKey() {
		return super.getKey();
	}

	@XmlAttribute
	public String getDisplay() {
		return tryTranslation(getKey());
	}

	@Override
	@XmlValue
	public String getValue() {
		return super.getValue();
	}
	
	@Override
	@XmlElement("extra")
	public Set<String> getExtraInfo() {
		return super.getExtraInfo();
	}

	public SimpleVariable(FeaturesCollection container, String key,
			FeatureCalculator<SimpleVariable> calculator) {
		super(container, key, calculator);
	}

	public SimpleVariable(FeaturesCollection container, String key) {
		super(container, key, FeatureCalculator.getDefaultInstance(SimpleVariable.class));
	}

	public SimpleVariable(String key, FeatureCalculator<SimpleVariable> calculator) {
		super(key, calculator);
	}

	public SimpleVariable(String key) {
		super(key, FeatureCalculator.getDefaultInstance(SimpleVariable.class));
	}

	public SimpleVariable() {
		this(null);
	}
}
