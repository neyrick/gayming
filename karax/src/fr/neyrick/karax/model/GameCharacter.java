package fr.neyrick.karax.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import fr.neyrick.karax.entities.generic.Game;

@XmlRootElement
public abstract class GameCharacter {

	private Long metaId;
	
	private String name;
	
	private String playerName;
	
	private Game game;
	
	private Date creationDate;
	
	private Date lastUpdate;

	private int experience;

	private int availableExperience;
	
	public Long getMetaId() {
		return metaId;
	}

	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getAvailableExperience() {
		return availableExperience;
	}

	public void setAvailableExperience(int availableExperience) {
		this.availableExperience = availableExperience;
	}
	
}
