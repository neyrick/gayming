package fr.neyrick.karax.model;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public abstract class ComplexContainerFeature<T extends CharacterFeature> extends ContainerFeature {

	private Map<String, CharacterFeature> featuresMap = new TreeMap<>();
	
	public ComplexContainerFeature(ContainerFeature parent, String key) {
		super(parent, key);
	}

	public ComplexContainerFeature(String key) {
		super(key);
	}

	private ComplexContainerFeature() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	protected abstract CharacterFeature createSubFeature(String subItemKey, CharacterEdit edit);
	
	@Override
	protected CharacterFeature addFeature(String subItemKey, CharacterEdit edit) {
		CharacterFeature feature = createSubFeature(subItemKey, edit);
		featuresMap.put(subItemKey, feature);
		return feature;
	}

	@Override
	protected CharacterFeature getSubFeature(String key) {
		return featuresMap.get(key);
	}

	
}
