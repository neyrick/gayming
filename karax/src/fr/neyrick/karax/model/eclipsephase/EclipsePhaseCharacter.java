package fr.neyrick.karax.model.eclipsephase;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EclipsePhaseCharacter extends GameCharacter {


	private StringFeature background;
	
	private StringFeature faction;
	
	private StringFeature gender;
	
	private Set<StringFeature> motivation;
	
	private List<VariableNumericFeature> aptitude;

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

	@XmlElementWrapper(name="Motivations")
	public Set<StringFeature> getMotivation() {
		return motivation;
	}

	public void setMotivation(Set<StringFeature> motivation) {
		this.motivation = motivation;
	}

	public void setAptitude(List<VariableNumericFeature> aptitude) {
		this.aptitude = aptitude;
	}

	@XmlElementWrapper(name="Aptitudes")
	public List<VariableNumericFeature> getAptitude() {
		return aptitude;
	}

}
