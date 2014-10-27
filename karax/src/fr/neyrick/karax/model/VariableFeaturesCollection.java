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
	
	private FeatureCalculator<T> defaultCalculator = null;
		
	public int getTotalIntValue() {
		int result = 0;
		for(Map.Entry<String, T> item : featuresMap.entrySet()) {
			result += item.getValue().getNumericValue().intValue();
		}
		return result;
	}
	
	protected FeatureCalculator<T> getExtraItemCalculator(CharacterEdit edit) {
		return defaultCalculator;
	}

	public FeatureCalculator<T> getDefaultCalculator() {
		return defaultCalculator;
	}

	public void setDefaultCalculator(FeatureCalculator<T> defaultCalculator) {
		this.defaultCalculator = defaultCalculator;
	}

	public VariableFeaturesCollection(FeaturesCollection parent, String key, Class<T> featureClass, FeatureCalculator<T> calculator) {
		super(parent, key);
		this.featureClass = featureClass;
		this.defaultCalculator = calculator;
	}

	public VariableFeaturesCollection(FeaturesCollection parent, String key, Class<T> featureClass) {
		super(parent, key);
		this.featureClass = featureClass;
	}

	public VariableFeaturesCollection(String key, Class<T> featureClass, FeatureCalculator<T> calculator) {
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
	    FeatureCalculator<T> calculator = getExtraItemCalculator(edit);
	    if (calculator != null) feature.setCalculator(calculator);
	    customizeExtraFeature(edit, feature);
	    return addFeature(feature);
	}

	protected void customizeExtraFeature(CharacterEdit edit, T newFeature) {
		
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

	public T getActualSubFeature(String key) {
		return featuresMap.get(key);
	}

	@XmlElement
	public Collection<T> getActualSubFeatures() {
		return featuresMap.values();
	}

	
}
