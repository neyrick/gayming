package fr.neyrick.karax.entities.generic;

import java.util.Date;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="charhistory")
@XmlRootElement
public class CharacterEdit {

	
	@Id
	@GeneratedValue
	private Long id;

	@XmlTransient
	@JsonIgnore
	@ManyToOne
	private MetaCharacter character;
	
	private String targetKey;
	
	private String targetSubKey1;
	
	private String targetSubKey2;
	
	private String targetSubKey3;
	
	private String value;
	
	@Embedded
	private Increment increment;
	
	private Date editDate;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private CharacterEdit cause;
	
	@OneToMany(mappedBy="cause")
	private Set<CharacterEdit> consequences;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public MetaCharacter getCharacter() {
		return character;
	}

	public void setCharacter(MetaCharacter character) {
		this.character = character;
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

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date date) {
		this.editDate = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CharacterEdit getCause() {
		return cause;
	}

	public void setCause(CharacterEdit cause) {
		this.cause = cause;
	}

	public Set<CharacterEdit> getConsequences() {
		return consequences;
	}

	public void setConsequences(Set<CharacterEdit> consequences) {
		this.consequences = consequences;
	}

	public Increment getIncrement() {
		return increment;
	}

	public void setIncrement(Increment increment) {
		this.increment = increment;
	}
	
}
