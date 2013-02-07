package fr.neyrick.karax.model;

import java.util.ResourceBundle;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public interface CharacterFeature {

	public void recordEdit(CharacterEdit edit);
	
	public String getKey();
	
	public FeaturesCollection getContainer();
	
	public void setResourceBundle(ResourceBundle bundle);
}
