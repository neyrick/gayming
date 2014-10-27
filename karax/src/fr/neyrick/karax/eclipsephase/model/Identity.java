package fr.neyrick.karax.eclipsephase.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.model.CharacterFeature;
import fr.neyrick.karax.model.ComplexFeatureCollection;
import fr.neyrick.karax.model.FeatureCalculator;
import fr.neyrick.karax.model.FeaturesCollection;
import fr.neyrick.karax.model.SimpleVariable;
import fr.neyrick.karax.model.StringFeature;
import fr.neyrick.karax.model.VariableFeaturesCollection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Identity extends ComplexFeatureCollection {

	public static final String SUBKEY_NAME="NAME";
	public static final String SUBKEY_NOTES="NOTES";
	public static final String SUBKEY_REPUTATION="REPUTATION";
	
	private StringFeature notes;

	private StringFeature name ;

	private VariableFeaturesCollection<SimpleVariable> reputations;
	
	@XmlElement
	public StringFeature getName() {
		return name;
	}

	@XmlElement
	public StringFeature getNotes() {
		return notes;
	}

	@XmlElementWrapper(name="reputations")
	@XmlElement(name="reputation")
	public Collection<SimpleVariable> getReputations() {
		return reputations.getActualSubFeatures();
	}

	private void initFields() {
		name = new StringFeature(this, SUBKEY_NAME);
		notes = new StringFeature(this, SUBKEY_NOTES);
		reputations =
				new VariableFeaturesCollection<>(this,  SUBKEY_REPUTATION, SimpleVariable.class, new FeatureCalculator<SimpleVariable>() {

					@Override
					public Number calculateFeature(SimpleVariable feature) {
						int result = feature.getCreationCost();
						result += (feature.getFreebieCost() * 10) + (feature.getExperienceCost() * 10);
						result += feature.getFreeCost();
						if ((feature.getModifier() < -500) && (result > 0)) return 0; 
						return result;
					}
					
				});
	}
	
	public Identity(FeaturesCollection parent, String key) {
		super(parent, key);
		initFields();
	}
	
	public Identity(String key) {
		super(key);
		initFields();
	}

	public Identity() {
		super(null);
		initFields();
	}

	@Override
	protected CharacterFeature createSubFeature(String subItemKey, CharacterEdit edit) {
		switch(subItemKey) {
			case SUBKEY_NAME:return name;
			case SUBKEY_NOTES:return notes;
			case SUBKEY_REPUTATION:return reputations;
			default:return null;				
		}
	}
}
