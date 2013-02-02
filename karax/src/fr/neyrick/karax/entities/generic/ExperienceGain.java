package fr.neyrick.karax.entities.generic;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="expgains")
@XmlRootElement
public class ExperienceGain {

	@Id
	@GeneratedValue
	private Long id;
	
	@XmlTransient
	@JsonIgnore
	@ManyToOne
	private MetaCharacter character;
	
	private int amount;
	
	private Date gainDate;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getGainDate() {
		return gainDate;
	}

	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}

}
