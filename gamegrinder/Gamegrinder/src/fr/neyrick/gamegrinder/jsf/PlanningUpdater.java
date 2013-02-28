package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.GameManager;
import fr.neyrick.gamegrinder.dao.NotesManager;
import fr.neyrick.gamegrinder.entities.Day;
import fr.neyrick.gamegrinder.entities.Game;
import fr.neyrick.gamegrinder.entities.Note;
import fr.neyrick.gamegrinder.entities.PlayerAvailability;
import fr.neyrick.gamegrinder.entities.Setting;
import fr.neyrick.gamegrinder.entities.TimeFrame;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class PlanningUpdater implements Serializable {

	private Set<Setting> currentSettings = new HashSet<Setting>();
	
	private List<Long> selectedPlayersId = new ArrayList<Long>();

	private Setting currentDetailSetting;
	
	private TimeFrame currentTimeFrame;
	
	@Inject
	private Visitor visitor;
	
	@Inject
	private GameManager gameManager;
	
	@Inject
	private NotesManager notesManager;
	
	@Inject
	private CalendarDisplay calendarDisplay;
	
	private Day currentDay;
	
	private String newNoteText;
	
	public void toggleCurrentSetting(Setting setting) {
		if (!currentSettings.remove(setting)) currentSettings.add(setting);
	}
	
	public boolean isSettingActive(Setting setting) {
		return currentSettings.contains(setting);
	}
	
	public TimeFrame getCurrentTimeFrame() {
		return currentTimeFrame;
	}

	public void setCurrentTimeFrame(TimeFrame currentTimeFrame) {
		this.currentTimeFrame = currentTimeFrame;
	}

	public String updateAvailability() {
		gameManager.clearAvailability(visitor.getName(), currentTimeFrame);
		for (Setting setting : currentSettings) {
			PlayerAvailability pa = new PlayerAvailability();
			pa.setPlayerName(visitor.getName());
			pa.setSetting(setting);
			pa.setTimeFrame(currentTimeFrame);
			gameManager.storeAvailability(pa);
		}
		calendarDisplay.getUserAvails().put(currentTimeFrame, currentSettings);
		return null;
	}
	
	public String addAvailability() {
		for (Setting setting : currentSettings) {
			PlayerAvailability pa = new PlayerAvailability();
			pa.setPlayerName(visitor.getName());
			pa.setSetting(setting);
			pa.setTimeFrame(currentTimeFrame);
			gameManager.storeAvailability(pa);
		}
		calendarDisplay.getUserAvails().put(currentTimeFrame, currentSettings);
		return null;
	}
	
	public String clearAvailability() {
		gameManager.clearAvailability(visitor.getName(), currentTimeFrame);
		calendarDisplay.getUserAvails().remove(currentTimeFrame);
		return null;
	}
	
	public Set<Setting> getCurrentSettings() {
		return currentSettings;
	}

	public Day getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(Day currentDay) {
		this.currentDay = currentDay;
	}

	public String addNote() {
		Note note = new Note();
		note.setAuthor(visitor.getName());
		note.setPostDate(new Date());
		note.setText(newNoteText);
		notesManager.storeNote(note);
		newNoteText = "";
		return null;
	}
	
	public String getNewNoteText() {
		return newNoteText;
	}

	public void setNewNoteText(String newNoteText) {
		this.newNoteText = newNoteText;
	}

	public Setting getCurrentDetailSetting() {
		return currentDetailSetting;
	}

	public List<Long> getSelectedPlayers() {
		return selectedPlayersId;
	}

	public void setSelectedPlayers(List<Long> selectedPlayers) {
		this.selectedPlayersId = selectedPlayers;
	}

	public void setCurrentDetailSetting(Setting currentDetailSetting) {
		this.currentDetailSetting = currentDetailSetting;
	}

	public String removeGame(Game game) {
		if (visitor.getName().equals(game.getGmname())) {
			gameManager.removeGame(game);			
		}
		else {
			gameManager.removePlayerFromGame(visitor.getName(), game);
		}
		return null;
	}	
	
	public String storeNewGame() {
		Game game = new Game();
		game.setSetting(currentDetailSetting);
		game.setGmname(visitor.getName());
		game.setTimeFrame(currentTimeFrame);
		gameManager.storeGame(game, selectedPlayersId);
		return null;
	}
	
	public PlanningUpdater() {
	}

}
