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
public class PlayerAvailability implements Serializable {

	@Id
	private Long id;
	
	private String playerName;
	
	@ManyToOne
	private Game game;
	
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

}
