package fr.neyrick.karax.model.eclipsephase;

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
import fr.neyrick.karax.model.VariableNumericFeature;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Identity extends ComplexFeatureCollection {

	public static final String SUBKEY_NAME="NAME";
	public static final String SUBKEY_NOTES="NOTES";
	public static final String SUBKEY_REPUTATION="REPUTATION";
	
	private StringFeature notes;

	private StringFeature name;

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

	public Identity(FeaturesCollection parent, String key) {
		super(parent, key);
	}
	
	public Identity(String key) {
		super(key);
	}

	public Identity() {
		super(null);
	}

	@Override
	protected CharacterFeature createSubFeature(String subItemKey, CharacterEdit edit) {
		switch(subItemKey) {
			case SUBKEY_NAME:return (name = new StringFeature(this, SUBKEY_NAME));
			case SUBKEY_NOTES:return (notes = new StringFeature(this, SUBKEY_NOTES));
			case SUBKEY_REPUTATION:return (reputations =
					new VariableFeaturesCollection<>(this,  SUBKEY_REPUTATION, SimpleVariable.class, new FeatureCalculator() {

						@Override
						public Number calculate(CharacterFeature feature) {
							VariableNumericFeature targetFeature = (VariableNumericFeature)feature;
							int result = targetFeature.getCreationCost();
							result += (targetFeature.getFreebieCost() * 10) + (targetFeature.getExperienceCost() * 10) + targetFeature.getFreeCost();
							return result;
						}
						
					}));
			default:return null;				
		}
	}
}
