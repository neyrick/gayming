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
	
	public SimpleContainerFeature(ContainerFeature parent, String key, Class<T> featureClass) {
		super(parent, key);
		this.featureClass = featureClass;
	}

	public SimpleContainerFeature(String key, Class<T> featureClass) {
		super(key);
		this.featureClass = featureClass;
	}

	private SimpleContainerFeature() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	@Override
	protected CharacterFeature addFeature(String subItemKey, CharacterEdit edit) {
	    T feature;
		try {
			feature = featureClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	    feature.setContainer(this);
	    feature.setKey(getSubItemKey(edit));
	    featuresMap.put(subItemKey, feature);
	    return feature;
	}

	@Override
	protected CharacterFeature getSubFeature(String key) {
		return featuresMap.get(key);
	}

	@XmlElement
	public Collection<T> getActualSubFeatures() {
		return featuresMap.values();
	}

	
}
