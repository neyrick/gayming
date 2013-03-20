package fr.neyrick.karax.eclipsephase.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Muse {

	private StringFeature name;
	
	private StaticFeaturesCollection<FixedNumericFeature> aptitudes;
	
	private StaticFeaturesCollection<FixedNumericFeature> skills;
	
	@XmlElement
	public StringFeature getName() {
		return name;
	}

	public void setName(StringFeature name) {
		this.name = name;
	}

	@XmlElementWrapper(name="aptitudes")
	@XmlElement(name="aptitude")
	public Collection<FixedNumericFeature> getAptitudes() {
		return aptitudes.getActualSubFeatures();
	}

	public void setAptitudes(StaticFeaturesCollection<FixedNumericFeature> aptitudes) {
		this.aptitudes = aptitudes;
	}

	@XmlElementWrapper(name="skills")
	@XmlElement(name="skill")
	public Collection<FixedNumericFeature> getSkills() {
		return skills.getActualSubFeatures();
	}

	public void setSkills(StaticFeaturesCollection<FixedNumericFeature> skills) {
		this.skills = skills;
	}

	@XmlElement
	public int getLucidity() {
		FixedNumericFeature wil = aptitudes.getActualSubFeature(EclipsePhaseCharacter.KEY_WIL);
		if (wil != null) return aptitudes.getActualSubFeature(EclipsePhaseCharacter.KEY_WIL).getNumericValue()*2;
		else return 0;
	}
	
	@XmlElement
	public int getTraumaThreshold() {
		return (int)Math.ceil(getLucidity() / 5.);
	}

	@XmlElement
	public int getInsanityRating() {
		return getLucidity()*2;
	}
		
}
