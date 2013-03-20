package fr.neyrick.karax.eclipsephase.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FixedNumericFeature;
import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EclipsePhaseCharacter extends GameCharacter {

	public static final String KEY_COG = "COG";
	public static final String KEY_COO = "COO";
	public static final String KEY_INT = "INT";
	public static final String KEY_SOM = "SOM";
	public static final String KEY_WIL = "WIL";
	public static final String KEY_REF = "REF";
	public static final String KEY_SAV = "SAV";
	
	private StringFeature background;
	
	private StringFeature faction;
	
	private StringFeature gender;
	
	private FixedNumericFeature actualAge;
	
	private StaticFeaturesCollection<StringFeature> motivations;
	
	private VariableFeaturesCollection<Aptitude> aptitudes;
	
	private EclipsePhaseSkillList activeSkills;
	
	private EclipsePhaseSkillList knowledgeSkills;
	
	private EclipsePhaseSkillList psiSkills;

	private SimpleVariable moxie;
	
	private SimpleVariable initiative;
	
	private StaticFeaturesCollection<Identity> identities;
	
	private Muse muse;

	private SimpleVariable credits;
	
	private Morph currentMorph;
	
	private StaticFeaturesCollection<Trait> traits;

	private StaticFeaturesCollection<FixedNumericFeature> psiSleights;
	
	private StaticFeaturesCollection<StringFeature> gear;
	
	private StaticFeaturesCollection<StringFeature> software;
	
	private StaticFeaturesCollection<StringFeature> contacts;
	

	@XmlElement
	public StringFeature getBackground() {
		return background;
	}

	public void setBackground(StringFeature background) {
		this.background = background;
	}

	@XmlElement
	public StringFeature getFaction() {
		return faction;
	}

	public void setFaction(StringFeature faction) {
		this.faction = faction;
	}

	@XmlElement
	public StringFeature getGender() {
		return gender;
	}

	public void setGender(StringFeature gender) {
		this.gender = gender;
	}

	public void setMotivations( StaticFeaturesCollection<StringFeature> motivations) {
		this.motivations = motivations;
	}

	public void setAptitudes(VariableFeaturesCollection<Aptitude> aptitudes) {
		this.aptitudes = aptitudes;
	}

	@XmlElementWrapper(name="aptitudes")
	@XmlElement(name="aptitude")
	public Collection<Aptitude> getAptitudes() {
		return aptitudes.getActualSubFeatures();
	}

	public void setActiveSkills(EclipsePhaseSkillList skills) {
		this.activeSkills = skills;
	}

	@XmlElementWrapper(name="activeskills")
	@XmlElement(name="skill")
	public Collection<Skill> getActiveSkills() {
		return activeSkills.getActualSubFeatures();
	}

	public void setKnowledgeSkills(EclipsePhaseSkillList skills) {
		this.knowledgeSkills = skills;
	}

	@XmlElementWrapper(name="knowledgeskills")
	@XmlElement(name="skill")
	public Collection<Skill> getKnowledgeSkills() {
		return knowledgeSkills.getActualSubFeatures();
	}

	public void setPsiSkills(EclipsePhaseSkillList skills) {
		this.psiSkills = skills;
	}

	@XmlElementWrapper(name="psiskills")
	@XmlElement(name="skill")
	public Collection<Skill> getPsiSkills() {
		return psiSkills.getActualSubFeatures();
	}

	@XmlElementWrapper(name="motivations")
	public Collection<StringFeature> getMotivation() {
		return motivations.getActualSubFeatures();
	}

	@XmlElement
	public FixedNumericFeature getActualAge() {
		return actualAge;
	}

	public void setActualAge(FixedNumericFeature actualAge) {
		this.actualAge = actualAge;
	}

	@XmlElement
	public SimpleVariable getMoxie() {
		return moxie;
	}

	public void setMoxie(SimpleVariable moxie) {
		this.moxie = moxie;
	}

	@XmlElement
	public SimpleVariable getInitiative() {
		return initiative;
	}

	public void setInitiative(SimpleVariable initiative) {
		this.initiative = initiative;
	}

	@XmlElement
	public SimpleVariable getCredits() {
		return credits;
	}

	public void setCredits(SimpleVariable credits) {
		this.credits = credits;
	}

	@XmlElementWrapper(name="identities")
	@XmlElement(name="identity")
	public Collection<Identity> getIdentities() {
		return identities.getActualSubFeatures();
	}

	public void setIdentities(StaticFeaturesCollection<Identity> identities) {
		this.identities = identities;
	}

	@XmlElement
	public Muse getMuse() {
		return muse;
	}

	public void setMuse(Muse muse) {
		this.muse = muse;
	}

	@XmlElement
	public Morph getCurrentMorph() {
		return currentMorph;
	}

	public void setCurrentMorph(Morph currentMorph) {
		this.currentMorph = currentMorph;
	}

	@XmlElementWrapper(name="traits")
	@XmlElement(name="trait")
	public Collection<Trait> getTraits() {
		return traits.getActualSubFeatures();
	}

	public void setTraits(StaticFeaturesCollection<Trait> traits) {
		this.traits = traits;
	}

	@XmlElementWrapper(name="psiSleights")
	@XmlElement(name="sleight")
	public Collection<FixedNumericFeature> getPsiSleights() {
		return psiSleights.getActualSubFeatures();
	}

	public void setPsiSleights(StaticFeaturesCollection<FixedNumericFeature> sleights) {
		this.psiSleights = sleights;
	}

	@XmlElement
	public int getLucidity() {
		return aptitudes.getActualSubFeature(KEY_WIL).getNumericValue().intValue()*2;
	}
	
	@XmlElement
	public int getTraumaThreshold() {
		return (int)Math.ceil(getLucidity() / 5.);
	}

	@XmlElement
	public int getInsanityRating() {
		return getLucidity()*2;
	}
	
	@XmlElement
	public int getDamageBonus() {
		return aptitudes.getActualSubFeature(KEY_SOM).getNumericValue().intValue()/10;
	}

	@XmlElementWrapper(name="gear")
	@XmlElement(name="item")
	public Collection<StringFeature> getGear() {
		return gear.getActualSubFeatures();
	}

	public void setGear(StaticFeaturesCollection<StringFeature> gear) {
		this.gear = gear;
	}

	@XmlElementWrapper(name="software")
	@XmlElement(name="item")
	public Collection<StringFeature> getSoftware() {
		return software.getActualSubFeatures();
	}

	public void setSoftware(StaticFeaturesCollection<StringFeature> software) {
		this.software = software;
	}

	@XmlElementWrapper(name="contacts")
	@XmlElement(name="contact")
	public Collection<StringFeature> getContacts() {
		return contacts.getActualSubFeatures();
	}

	public void setContacts(StaticFeaturesCollection<StringFeature> contacts) {
		this.contacts = contacts;
	}
	
}
