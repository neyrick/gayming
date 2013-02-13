package fr.neyrick.karax.model;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class AbstractFeature implements CharacterFeature {

	private FeaturesCollection container = null;

	private ResourceBundle resourceBundle;

	private String key;

	public String getKey() {
		return key;
	}

	protected String tryTranslation(String value) {
		try {
			return getResourceBundle().getString(value);
		} catch (MissingResourceException e) {
			return value;
		}
	}
	
	public ResourceBundle getResourceBundle() {
		if (resourceBundle == null) {
			if (container != null) return container.getResourceBundle();
		}
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
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
		if (container != null) this.setContainer(container);
	}
}
