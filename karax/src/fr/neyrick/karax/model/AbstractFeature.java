package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlTransient;


@XmlTransient
public abstract class AbstractFeature implements CharacterFeature {

	private FeaturesCollection container = null;
	
	private String key;
	
	public String getKey() {
		return key;
	}
	
	public FeaturesCollection getContainer() {
		return container;
	}

	public void setContainer(FeaturesCollection container) {
		this.container = container;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public AbstractFeature(FeaturesCollection container, String key) {
		super();
		this.key = key;
		this.container = container;
	}
}
