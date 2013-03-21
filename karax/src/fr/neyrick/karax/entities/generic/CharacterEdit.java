package fr.neyrick.karax.entities.generic;

import java.util.Date;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CharacterEdit {

	@Id
	@GeneratedValue
	private Long id;

	private String targetKey;
	
	private String targetSubKey1;
	
	private String targetSubKey2;
	
	private String targetSubKey3;
	
	private String value;
	
	@Embedded
	private Increment increment;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTargetKey() {
		return targetKey;
	}

	public void setTargetKey(String targetKey) {
		this.targetKey = targetKey;
	}
	
	public String getTargetSubKey1() {
		return targetSubKey1;
	}

	public void setTargetSubKey1(String targetSubKey1) {
		this.targetSubKey1 = targetSubKey1;
	}

	public String getTargetSubKey2() {
		return targetSubKey2;
	}

	public void setTargetSubKey2(String targetSubKey2) {
		this.targetSubKey2 = targetSubKey2;
	}

	public String getTargetSubKey3() {
		return targetSubKey3;
	}

	public void setTargetSubKey3(String targetSubKey3) {
		this.targetSubKey3 = targetSubKey3;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public abstract CharacterEdit getCause();

	public abstract Set<? extends CharacterEdit> getConsequences();
	
	public abstract Date getEditDate();

	public Increment getIncrement() {
		return increment;
	}

	public void setIncrement(Increment increment) {
		this.increment = increment;
	}
	
}
