package fr.neyrick.karax.model.eclipsephase;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Morph {

	public static final String ORIGIN_BIO = "BIO";
	public static final String ORIGIN_POD = "POD";
	public static final String ORIGIN_INFO = "INFO";
	public static final String ORIGIN_SYNTH = "SYNTH";
	
	
	private StringFeature type;
	
	private FixedNumericFeature aptitudeMax;

	private FixedNumericFeature speedModifier;

	private SimpleVariable durability;

	private StringFeature movementRate;

	private FixedNumericFeature visibleAge;
	
	private StringFeature description;
	
	private StringFeature sex;
	
	private StaticFeaturesCollection<Trait> traits;
	
	private StaticFeaturesCollection<StringFeature> enhancements;
	
	private StringFeature origin;
	
	@XmlElement
	public StringFeature getType() {
		return type;
	}

	public void setType(StringFeature type) {
		this.type = type;
	}

	@XmlElement
	public StringFeature getOrigin() {
		return origin;
	}

	public void setOrigin(StringFeature origin) {
		this.origin = origin;
	}

	@XmlElement
	public FixedNumericFeature getAptitudeMax() {
		return aptitudeMax;
	}

	public void setAptitudeMax(FixedNumericFeature aptitudeMax) {
		this.aptitudeMax = aptitudeMax;
	}

	@XmlElement
	public FixedNumericFeature getSpeedModifier() {
		return speedModifier;
	}

	public void setSpeedModifier(FixedNumericFeature speedModifier) {
		this.speedModifier = speedModifier;
	}

	@XmlElement
	public SimpleVariable getDurability() {
		return durability;
	}

	public void setDurability(SimpleVariable durability) {
		this.durability = durability;
	}

	@XmlElement
	public StringFeature getMovementRate() {
		return movementRate;
	}

	public void setMovementRate(StringFeature movementRate) {
		this.movementRate = movementRate;
	}

	@XmlElement
	public FixedNumericFeature getVisibleAge() {
		return visibleAge;
	}

	public void setVisibleAge(FixedNumericFeature visibleAge) {
		this.visibleAge = visibleAge;
	}

	@XmlElement
	public StringFeature getDescription() {
		return description;
	}

	public void setDescription(StringFeature description) {
		this.description = description;
	}

	@XmlElement
	public StringFeature getSex() {
		return sex;
	}

	public void setSex(StringFeature sex) {
		this.sex = sex;
	}

	@XmlElement
	public int getSpeed() {
		return 1 + speedModifier.getNumericValue();
	}

	@XmlElementWrapper(name="traits")
	@XmlElement(name="trait")
	public Collection<Trait> getTraits() {
		return traits.getActualSubFeatures();
	}

	public void setTraits(StaticFeaturesCollection<Trait> traits) {
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
	
	@XmlElement
	public int getWoundThreshold() {
		return (int)Math.ceil(durability.getNumericValue().intValue() / 5);
	}
	
	@XmlElement
	public int getDeathRating() {
		double factor = 0;
		if (ORIGIN_SYNTH.equals(origin.getValue())) factor = 2.;
		else factor = 1.5;
		return (int)(durability.getNumericValue().intValue() * factor);
	}
}
