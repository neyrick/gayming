package fr.neyrick.karax.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;
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

	@XmlTransient
	private Map<String, EditListener> editListenersMap = new TreeMap<>();
	
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
	
	protected void addEditListener(EditListener listener) {
		editListenersMap.put(listener.getKey(), listener);
	}
	
	public void processEdits(List<CharacterEdit> edits) {
		EditListener listener = null;
		for(CharacterEdit edit : edits) {
			listener = editListenersMap.get(edit.getTargetKey());
			if (listener != null) listener.recordEdit(edit);
		}
	}
	
}
