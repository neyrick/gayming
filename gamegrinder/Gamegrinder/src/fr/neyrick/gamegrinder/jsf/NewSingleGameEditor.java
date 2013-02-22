package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import fr.neyrick.gamegrinder.entities.Game;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class NewSingleGameEditor implements Serializable {

	private Game game = new Game();
	
	private String initialNote;
	
	private boolean active = false;
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getInitialNote() {
		return initialNote;
	}

	public void setInitialNote(String initialNote) {
		this.initialNote = initialNote;
	}

	public String saveGame() {
//		game.getNotes().clear();
//		game.addNote(game.getGmname(), initialNote);
		setActive(false);
		return null;
	}
	
	public String launch() {
		setActive(true);
		return null;
	}
	
	public String cancel() {
		setActive(false);
		return null;
	}
		
	public NewSingleGameEditor() {
	}

}
