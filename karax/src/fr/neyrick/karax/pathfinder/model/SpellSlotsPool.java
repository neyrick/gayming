package fr.neyrick.karax.pathfinder.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.SimpleVariable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SpellSlotsPool extends SimpleVariable {

	@XmlAttribute
	public String getLevel() {
		return this.getKey();
	}
	
	public SpellSlotsPool(FeaturesCollection container, String key,
			FeatureCalculator calculator) {
		super(container, key, calculator);
	}

	public SpellSlotsPool(FeaturesCollection container, String key) {
		super(container, key);
	}

	public SpellSlotsPool(String key, FeatureCalculator calculator) {
		super(key, calculator);
	}

	public SpellSlotsPool(String key) {
		super(key);
	}

	public SpellSlotsPool() {
	}

}
