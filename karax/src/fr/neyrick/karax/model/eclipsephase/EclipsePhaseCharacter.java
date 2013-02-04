package fr.neyrick.karax.model.eclipsephase;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.SimpleContainerFeature;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EclipsePhaseCharacter extends GameCharacter {


	private StringFeature background;
	
	private StringFeature faction;
	
	private StringFeature gender;
	
	private SimpleContainerFeature<StringFeature> motivations;
	
	private List<VariableNumericFeature> aptitudeBase;
	
	private List<VariableNumericFeature> aptitudeTotal;
	
	private List<VariableNumericFeature> activeSkills;
	
	private List<VariableNumericFeature> knowledgeSkills;
	
	private List<VariableNumericFeature> psiSkills;
	
	private VariableNumericFeature moxie;

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

	public void setMotivations( SimpleContainerFeature<StringFeature> motivations) {
		this.motivations = motivations;
	}

	public void setAptitudeBase(List<VariableNumericFeature> aptitude) {
		this.aptitudeBase = aptitude;
	}

	@XmlElementWrapper(name="aptitudesbase")
	public List<VariableNumericFeature> getAptitudeBase() {
		return aptitudeBase;
	}

	public void setAptitudeTotal(List<VariableNumericFeature> aptitude) {
		this.aptitudeTotal = aptitude;
	}

	@XmlElementWrapper(name="aptitudestotal")
	public List<VariableNumericFeature> getAptitudeTotal() {
		return aptitudeTotal;
	}

	public void setActiveSkills(List<VariableNumericFeature> skills) {
		this.activeSkills = skills;
	}

	@XmlElementWrapper(name="activeskills")
	public List<VariableNumericFeature> getActiveSkill() {
		return activeSkills;
	}

	public void setKnowledgeSkills(List<VariableNumericFeature> skills) {
		this.knowledgeSkills = skills;
	}

	@XmlElementWrapper(name="knowledgeskills")
	public List<VariableNumericFeature> getKnowledgeSkill() {
		return knowledgeSkills;
	}

	public void setPsiSkills(List<VariableNumericFeature> skills) {
		this.psiSkills = skills;
	}

	@XmlElementWrapper(name="psiskills")
	public List<VariableNumericFeature> getPsiSkill() {
		return psiSkills;
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

}
