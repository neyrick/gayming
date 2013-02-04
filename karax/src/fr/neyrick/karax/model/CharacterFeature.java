package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;

@XmlTransient
public interface CharacterFeature {

	public void recordEdit(CharacterEdit edit);
	
	public String getKey();
	
	public ContainerFeature getContainer();
}
