package fr.neyrick.karax.pathfinder.model;

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
public class ArmorClass extends VariableNumericFeature {

	private int flatfooted = 0;
	
	private int touch = 0;
	
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
	public int getFlatfooted() {
		refresh();
		return flatfooted;
	}

	public void setFlatfooted(int flatfooted) {
		this.flatfooted = flatfooted;
	}

	@XmlAttribute
	public int getTouch() {
		refresh();
		return touch;
	}

	public void setTouch(int touch) {
		this.touch = touch;
	}

	public ArmorClass() {
		this(null, null);
	}

	public ArmorClass(FeaturesCollection container, String key,
			FeatureCalculator calculator) {
		super(container, key, calculator);
	}

	public ArmorClass(String key, FeatureCalculator calculator) {
		super(key, calculator);
	}

	
}
