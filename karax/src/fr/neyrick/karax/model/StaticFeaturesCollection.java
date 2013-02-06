package fr.neyrick.karax.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public class StaticFeaturesCollection<T extends AbstractFeature> extends FeaturesCollection {

	private Map<String, T> featuresMap = new TreeMap<>();
	
	private final Class<? extends T> featureClass;
	
	public StaticFeaturesCollection(FeaturesCollection parent, String key, Class<T> featureClass) {
		super(parent, key);
		this.featureClass = featureClass;
	}

	public StaticFeaturesCollection(String key, Class<T> featureClass) {
		super(key);
		this.featureClass = featureClass;
	}

	protected StaticFeaturesCollection() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	public CharacterFeature addFeature(String subItemKey) {
		return this.addFeature(subItemKey, null);
	}

	@Override
	public CharacterFeature addFeature(String subItemKey, CharacterEdit edit) {
	    T feature;
		try {
			feature = featureClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	    feature.setKey(subItemKey);
	    return addFeature(feature);
	}

	public CharacterFeature addFeature(T feature) {
	    feature.setContainer(this);
	    featuresMap.put(feature.getKey(), feature);
	    return feature;
	}

	@Override
	public CharacterFeature getSubFeature(String key) {
		return featuresMap.get(key);
	}

	@XmlElement
	public Collection<T> getActualSubFeatures() {
		return featuresMap.values();
	}

	
}
