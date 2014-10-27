package fr.neyrick.karax.pathfinder.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.FeaturesCollection;

@XmlTransient
public class SpellSlotsMap extends FeaturesCollection {

	private Map<String, SpellSlotsArray> innerMap = new TreeMap<>();
	
	public SpellSlotsMap(FeaturesCollection parent, String key) {
		super(parent, key);
	}

	public SpellSlotsMap(String key) {
		super(key);
	}

	@Override
	public CharacterFeature addFeature(String subItemKey, CharacterEdit edit) {
		SpellSlotsArray spellSlotsArray = new SpellSlotsArray(subItemKey);
		spellSlotsArray.setContainer(this);
		innerMap.put(spellSlotsArray.getKey(), spellSlotsArray);
	    return spellSlotsArray;
	}

	@Override
	public CharacterFeature getSubFeature(String key) {
		return innerMap.get(key);
	}

	@XmlElement
	public Collection<SpellSlotsArray> getActualSubFeatures() {
		return innerMap.values();
	}

}
