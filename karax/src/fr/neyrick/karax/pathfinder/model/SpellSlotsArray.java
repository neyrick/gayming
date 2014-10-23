package fr.neyrick.karax.pathfinder.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.VariableFeaturesCollection;

public class SpellSlotsArray extends VariableFeaturesCollection<SimpleVariable> {

	@XmlAttribute
	public String getCasterclass() {
		return this.getKey();
	}
	
	@XmlElement(name="pool")
	public Collection<SimpleVariable> getPools() {
		return this.getActualSubFeatures();
	}

	public SpellSlotsArray(String key) {
		super(key, SimpleVariable.class);
	}


}
