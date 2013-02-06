package fr.neyrick.karax.model.eclipsephase;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.StaticFeaturesCollection;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso({Identity.class})
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
	
	private StaticFeaturesCollection<StringFeature> motivations;
	
	private VariableFeaturesCollection<Aptitude> aptitudes;
	
	private EclipsePhaseSkillList activeSkills;
	
	private EclipsePhaseSkillList knowledgeSkills;
	
	private EclipsePhaseSkillList psiSkills;

	private VariableNumericFeature moxie;
	
	private StaticFeaturesCollection<Identity> identities;

	public StringFeature getBackground() {
		return background;
	}

	public void setBackground(StringFeature background) {
		this.background = background;
	}

	public StringFeature getFaction() {
		return faction;
	}

	public void setFaction(StringFeature faction) {
		this.faction = faction;
	}

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

	public VariableNumericFeature getMoxie() {
		return moxie;
	}

	public void setMoxie(VariableNumericFeature moxie) {
		this.moxie = moxie;
	}

	@XmlElementWrapper(name="identities")
	@XmlElement(name="identity")
	public Collection<Identity> getIdentities() {
		return identities.getActualSubFeatures();
	}

	public void setIdentities(StaticFeaturesCollection<Identity> identities) {
		this.identities = identities;
	}


}
