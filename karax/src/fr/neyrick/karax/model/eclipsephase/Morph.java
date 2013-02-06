package fr.neyrick.karax.model.eclipsephase;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Morph {

	public static final String ORIGIN_BIO = "BIO";
	public static final String ORIGIN_SYNTH = "SYNTH";
	
	
	private StringFeature type;
	
	private FixedNumericFeature aptitudeMax;

	private FixedNumericFeature speedModifier;

	private FixedNumericFeature durability;

	private FixedNumericFeature movementRate;

	private FixedNumericFeature visibleAge;
	
	private StringFeature description;
	
	private StringFeature sex;
	
	private VariableNumericFeature speed;
	
	private StaticFeaturesCollection<StringFeature> traits;
	
	private StaticFeaturesCollection<StringFeature> enhancements;
	
	private StringFeature origin;
	
	public StringFeature getType() {
		return type;
	}

	public void setType(StringFeature type) {
		this.type = type;
	}

	public StringFeature getOrigin() {
		return origin;
	}

	public void setOrigin(StringFeature origin) {
		this.origin = origin;
	}

	public FixedNumericFeature getAptitudeMax() {
		return aptitudeMax;
	}

	public void setAptitudeMax(FixedNumericFeature aptitudeMax) {
		this.aptitudeMax = aptitudeMax;
	}

	public FixedNumericFeature getSpeedModifier() {
		return speedModifier;
	}

	public void setSpeedModifier(FixedNumericFeature speedModifier) {
		this.speedModifier = speedModifier;
	}

	public FixedNumericFeature getDurability() {
		return durability;
	}

	public void setDurability(FixedNumericFeature durability) {
		this.durability = durability;
	}

	public FixedNumericFeature getMovementRate() {
		return movementRate;
	}

	public void setMovementRate(FixedNumericFeature movementRate) {
		this.movementRate = movementRate;
	}

	public FixedNumericFeature getVisibleAge() {
		return visibleAge;
	}

	public void setVisibleAge(FixedNumericFeature visibleAge) {
		this.visibleAge = visibleAge;
	}

	public StringFeature getDescription() {
		return description;
	}

	public void setDescription(StringFeature description) {
		this.description = description;
	}

	public StringFeature getSex() {
		return sex;
	}

	public void setSex(StringFeature sex) {
		this.sex = sex;
	}

	public VariableNumericFeature getSpeed() {
		return speed;
	}

	public void setSpeed(VariableNumericFeature speed) {
		this.speed = speed;
	}

	@XmlElementWrapper(name="traits")
	@XmlElement(name="trait")
	public Collection<StringFeature> getTraits() {
		return traits.getActualSubFeatures();
	}

	public void setTraits(StaticFeaturesCollection<StringFeature> traits) {
		this.traits = traits;
	}

	@XmlElementWrapper(name="enhancements")
	@XmlElement(name="enhancement")
	public Collection<StringFeature> getEnhancements() {
		return enhancements.getActualSubFeatures();
	}

	public void setEnhancements(StaticFeaturesCollection<StringFeature> enhancements) {
		this.enhancements = enhancements;
	}
	
	public int getWoundThreshold() {
		return (int)Math.ceil(durability.getNumericValue() / 5);
	}
	
	public int getDeathRating() {
		double factor = 0;
		if (ORIGIN_BIO.equals(origin.getValue())) factor = 1.5;
		else if (ORIGIN_SYNTH.equals(origin.getValue())) factor = 2.; 
		return (int)(durability.getNumericValue() * factor);
	}
}
