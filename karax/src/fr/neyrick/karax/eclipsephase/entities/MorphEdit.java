package fr.neyrick.karax.eclipsephase.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.neyrick.karax.entities.generic.CharacterEdit;
import fr.neyrick.karax.entities.generic.Increment;

/**
 * Entity implementation class for Entity: MorphEdit
 *
 */
@Entity
@Table(name="EP_MorphEdit")
public class MorphEdit implements CharacterEdit, Serializable {

	private static final long serialVersionUID = 1L;

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
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MorphEdit cause;
	
	@OneToMany(mappedBy="cause")
	private Set<MorphEdit> consequences;
	

	@ManyToOne
	private MorphDefinition morph;
	
	public MorphDefinition getMorph() {
		return morph;
	}

	public void setMorph(MorphDefinition morph) {
		this.morph = morph;
	}

	public Long getId() {
		return id;
	}

	public String getTargetKey() {
		return targetKey;
	}

	public String getTargetSubKey1() {
		return targetSubKey1;
	}

	public String getTargetSubKey2() {
		return targetSubKey2;
	}

	public String getTargetSubKey3() {
		return targetSubKey3;
	}

	public String getValue() {
		return value;
	}

	public Increment getIncrement() {
		return increment;
	}

	public MorphEdit getCause() {
		return cause;
	}

	public MorphEdit() {
		super();
	}

	public Set<CharacterEdit> getConsequences() {
		return new HashSet<CharacterEdit>(consequences);
	}

	@Override
	public Date getEditDate() {
		return new Date();
	}
   
}
