package fr.neyrick.gamegrinder.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Game
 * 
 */
@Entity
@Table(name = "game")
@NamedQuery(name = "fetchGames", query = "select g from Game g join fetch g.players join fetch g.notes where g.timeFrame.dayDate between ?1 and ?2")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum GameStatus {
		TENTATIVE, CONFIRMED, CANCELLED
	};

	@Id
	private long id;

	private String setting;

	private String gmname;

	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	private Set<PlayerAvailability> players = new HashSet<PlayerAvailability>();
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Note> notes = new HashSet<Note>();
	
	@Embedded
	private TimeFrame timeFrame = new TimeFrame();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public String getGmname() {
		return gmname;
	}

	public void setGmname(String gmname) {
		this.gmname = gmname;
	}

	public Set<PlayerAvailability> getPlayers() {
		return players;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void addNote(String author, String text) {
		Note note = new Note(author, text, this);
		notes.add(note);
	}
	
	public Game() {
		super();
	}

}
