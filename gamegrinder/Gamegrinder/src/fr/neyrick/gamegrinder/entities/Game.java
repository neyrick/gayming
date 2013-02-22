package fr.neyrick.gamegrinder.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Game
 * 
 */
@Entity
@Table(name = "game")
@NamedQuery(name = "fetchGames", query = "select g from Game g join fetch g.players where g.timeFrame.dayDate between ?1 and ?2")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum GameStatus {
		TENTATIVE, CONFIRMED, CANCELLED
	};

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch=FetchType.EAGER)
	private Setting setting;

	private String gmname;

	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	private Set<PlayerAvailability> players = new HashSet<PlayerAvailability>();
	
	@Embedded
	private TimeFrame timeFrame = new TimeFrame();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
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

	public Game() {
		super();
	}

}
