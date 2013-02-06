package fr.neyrick.karax.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public abstract class ComplexFeatureCollection extends FeaturesCollection {

	private Map<String, CharacterFeature> featuresMap = new TreeMap<>();
	
	public ComplexFeatureCollection(FeaturesCollection parent, String key) {
		super(parent, key);
	}

	public ComplexFeatureCollection(String key) {
		super(key);
	}

	private ComplexFeatureCollection() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	protected abstract CharacterFeature createSubFeature(String subItemKey, CharacterEdit edit);
	
	@Override
	public CharacterFeature addFeature(String subItemKey, CharacterEdit edit) {
		CharacterFeature feature = createSubFeature(subItemKey, edit);
		featuresMap.put(subItemKey, feature);
		return feature;
	}

	@Override
	public CharacterFeature getSubFeature(String key) {
		return featuresMap.get(key);
	}

	protected Collection<CharacterFeature> getFeatures() {
		return featuresMap.values();
	}
}
