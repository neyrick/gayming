package fr.neyrick.karax.entities.generic;

import java.util.Date;
import java.util.Set;

public interface CharacterEdit {

	
	public Long getId();

	public String getTargetKey();
	
	public String getTargetSubKey1();

	public String getTargetSubKey2();

	public String getTargetSubKey3();
	
	public Date getEditDate();

	public String getValue();

	public CharacterEdit getCause();
	
	public Set<CharacterEdit> getConsequences();
	
	public Increment getIncrement();
	
}
