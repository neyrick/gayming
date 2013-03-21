package fr.neyrick.karax.eclipsephase.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.neyrick.karax.entities.generic.CharacterEdit;

/**
 * Entity implementation class for Entity: MorphEdit
 *
 */
@Entity
@Table(name="EP_MorphEdit")
public class MorphEdit extends CharacterEdit implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public MorphEdit getCause() {
		return cause;
	}

	public MorphEdit() {
		super();
	}

	public Set<? extends CharacterEdit> getConsequences() {
		return consequences;
	}

	@Override
	public Date getEditDate() {
		return new Date();
	}
   
}
