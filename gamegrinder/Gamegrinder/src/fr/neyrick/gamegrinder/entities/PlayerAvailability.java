package fr.neyrick.gamegrinder.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PlayerAvailability
 * 
 */
@Entity
@Table(name = "player_avail")
@NamedQuery(name = "fetchPlayers", query = "select pa from PlayerAvailability pa left join fetch pa.game where pa.timeFrame.dayDate between ?1 and ?2")
public class PlayerAvailability implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String playerName;
	
	@ManyToOne
	private Game game;
	
	@ManyToOne
	private Setting setting;
	
	private static final long serialVersionUID = 1L;
	
	@Embedded
	private TimeFrame timeFrame;

	public PlayerAvailability() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public TimeFrame getTimeFrame() {
		return this.timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

}
