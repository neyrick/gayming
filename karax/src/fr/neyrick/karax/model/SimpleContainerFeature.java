package fr.neyrick.karax.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public class SimpleContainerFeature<T extends AbstractFeature> extends ContainerFeature {

	private Map<String, T> featuresMap = new TreeMap<>();
	
	private final Class<? extends T> featureClass;
	
	private FeatureCalculator calculator = null;
	
	public SimpleContainerFeature(ContainerFeature parent, String key, Class<T> featureClass, FeatureCalculator calculator) {
		super(parent, key);
		this.featureClass = featureClass;
		this.calculator = calculator;
		
	}

	public SimpleContainerFeature(ContainerFeature parent, String key, Class<T> featureClass) {
		super(parent, key);
		this.featureClass = featureClass;
	}

	public SimpleContainerFeature(String key, Class<T> featureClass, FeatureCalculator calculator) {
		super(key);
		this.featureClass = featureClass;
		this.calculator = calculator;
	}

	public SimpleContainerFeature(String key, Class<T> featureClass) {
		super(key);
		this.featureClass = featureClass;
	}

	protected SimpleContainerFeature() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	@Override
	public CharacterFeature addFeature(String subItemKey, CharacterEdit edit) {
	    T feature;
		try {
			feature = featureClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	    feature.setContainer(this);
	    feature.setKey(subItemKey);
	    if (calculator != null) feature.setCalculator(calculator);
	    featuresMap.put(subItemKey, feature);
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
