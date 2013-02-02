package fr.neyrick.karax.model;

import fr.neyrick.karax.entities.generic.CharacterEdit;

public interface EditListener {

	public void recordEdit(CharacterEdit edit);
	
	public String getKey();
}
