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

	private int shield = 0;
	private int armor = 0;
	private int size = 0;
	private int natural = 0;
	private int deflection = 0;
	private int misc = 0;
	
	private int flatfooted = 0;
	private int touch = 0;
		
	
	@XmlAttribute
	public int getShield() {
		refresh();
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	@XmlAttribute
	public int getArmor() {
		refresh();
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	@XmlAttribute
	public int getSize() {
		refresh();
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@XmlAttribute
	public int getNatural() {
		refresh();
		return natural;
	}

	public void setNatural(int natural) {
		this.natural = natural;
	}

	@XmlAttribute
	public int getDeflection() {
		refresh();
		return deflection;
	}

	public void setDeflection(int deflection) {
		this.deflection = deflection;
	}

	@XmlAttribute
	public int getMisc() {
		refresh();
		return misc;
	}

	public void setMisc(int misc) {
		this.misc = misc;
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
			FeatureCalculator<ArmorClass> calculator) {
		super(container, key, calculator);
	}

	public ArmorClass(String key, FeatureCalculator<ArmorClass> calculator) {
		super(key, calculator);
	}

	
}
