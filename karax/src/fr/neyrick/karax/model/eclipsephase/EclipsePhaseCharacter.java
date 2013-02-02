package fr.neyrick.karax.model.eclipsephase;

import java.util.Set;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.GameCharacter;
import fr.neyrick.karax.model.StringFeature;

@XmlRootElement
public class EclipsePhaseCharacter extends GameCharacter {


	private StringFeature background;
	
	private StringFeature faction;
	
	private StringFeature gender;
	
	@XmlElementWrapper(name="Motivations")
	private Set<String> motivation;

	public StringFeature getBackground() {
		return background;
	}

	public void setBackground(StringFeature background) {
		addEditListener(background);
		this.background = background;
	}

	public StringFeature getFaction() {
		return faction;
	}

	public void setFaction(StringFeature faction) {
		addEditListener(faction);
		this.faction = faction;
	}

}
