package fr.neyrick.karax.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;



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
	public String getValue() {
		for (Object child : content) {
			if (child instanceof String) {
				child.toString();
			}
		}
		return null;
	}
	
	@Override
	public Set<String> getExtraInfo() {
		Set<String> result = new HashSet<String>();
		for (Object child : content) {
			if (child instanceof ExtraInfo) {
				result.add(((ExtraInfo)child).getValue());
			}
		}
		return result;
	}

    @XmlMixed 
    @XmlElementRefs({
            @XmlElementRef(name="extra", type=ExtraInfo.class)
    })
    List<?> content;
    
	public SimpleVariable(FeaturesCollection container, String key,
			FeatureCalculator<? extends SimpleVariable> calculator) {
		super(container, key, calculator);
	}

	public SimpleVariable(FeaturesCollection container, String key) {
		super(container, key, FeatureCalculator.getDefaultInstance(SimpleVariable.class));
	}

	public SimpleVariable(String key, FeatureCalculator<? extends SimpleVariable> calculator) {
		super(key, calculator);
	}

	public SimpleVariable(String key) {
		super(key, FeatureCalculator.getDefaultInstance(SimpleVariable.class));
	}

	public SimpleVariable() {
		this(null);
	}
}
