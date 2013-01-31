package fr.neyrick.karax.entities.generic;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ExperienceGain {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private MetaCharacter character;
	
	private int amount;
	
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
