package fr.neyrick.karax.entities.generic;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="charhistory")
@XmlRootElement
public class BaseCharacterEdit extends CharacterEdit {

	@XmlTransient
	@JsonIgnore
	@ManyToOne
	private MetaCharacter character;

	private Date editDate;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private BaseCharacterEdit cause;
	
	@OneToMany(mappedBy="cause")
	private Set<BaseCharacterEdit> consequences;
	

	public MetaCharacter getCharacter() {
		return character;
	}

	public void setCharacter(MetaCharacter character) {
		this.character = character;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date date) {
		this.editDate = date;
	}

	public BaseCharacterEdit getCause() {
		return cause;
	}

	public void setCause(BaseCharacterEdit cause) {
		this.cause = cause;
	}

	public Set<BaseCharacterEdit> getConsequences() {
		return consequences;
	}

	public void setConsequences(Set<BaseCharacterEdit> consequences) {
		this.consequences = consequences;
	}

}
