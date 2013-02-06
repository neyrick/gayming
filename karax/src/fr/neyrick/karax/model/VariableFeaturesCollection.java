package fr.neyrick.karax.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public class VariableFeaturesCollection<T extends VariableNumericFeature> extends FeaturesCollection {

	private Map<String, T> featuresMap = new TreeMap<>();
	
	private final Class<? extends T> featureClass;
	
	private FeatureCalculator defaultCalculator = null;
		
	protected FeatureCalculator getExtraItemCalculator(CharacterEdit edit) {
		return defaultCalculator;
	}

	public FeatureCalculator getDefaultCalculator() {
		return defaultCalculator;
	}

	public void setDefaultCalculator(FeatureCalculator defaultCalculator) {
		this.defaultCalculator = defaultCalculator;
	}

	public VariableFeaturesCollection(FeaturesCollection parent, String key, Class<T> featureClass, FeatureCalculator calculator) {
		super(parent, key);
		this.featureClass = featureClass;
		this.defaultCalculator = calculator;
	}

	public VariableFeaturesCollection(FeaturesCollection parent, String key, Class<T> featureClass) {
		super(parent, key);
		this.featureClass = featureClass;
	}

	public VariableFeaturesCollection(String key, Class<T> featureClass, FeatureCalculator calculator) {
		super(key);
		this.featureClass = featureClass;
		this.defaultCalculator = calculator;
	}

	public VariableFeaturesCollection(String key, Class<T> featureClass) {
		super(key);
		this.featureClass = featureClass;
	}

	protected VariableFeaturesCollection() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	@Override
	public T addFeature(String subItemKey, CharacterEdit edit) {
	    T feature;
		try {
			feature = featureClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	    feature.setKey(subItemKey);
	    feature.setCalculator(getExtraItemCalculator(edit));
	    return addFeature(feature);
	}

	public T addFeature(String subItemKey) {
		return this.addFeature(subItemKey, null);
	}

	public T addFeature(T feature) {
	    feature.setContainer(this);
	    featuresMap.put(feature.getKey(), feature);
	    if (feature.getCalculator() == null) feature.setCalculator(defaultCalculator);
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
