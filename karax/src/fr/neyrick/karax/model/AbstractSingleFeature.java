package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlTransient;


@XmlTransient
public abstract class AbstractSingleFeature extends AbstractFeature {

	public AbstractSingleFeature(FeaturesCollection container, String key) {
		super(container, key);
	}

	public AbstractSingleFeature(String key) {
		super(null, key);
	}

	public abstract String getValue();
	
}
