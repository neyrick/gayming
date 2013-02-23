package fr.neyrick.gamegrinder.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import fr.neyrick.gamegrinder.entities.TimeFrame.TimeFrameLocator;

public class Day {

	private Calendar date = Calendar.getInstance();
	
	private Map<TimeFrameLocator, List<PlayerAvailability>> players = new HashMap<TimeFrameLocator, List<PlayerAvailability>>();

	private List<Game> games = new ArrayList<Game>();
	
	private static final List<Integer> PLAY_DAYS = Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY);

	private List<Note> notes = new ArrayList<Note>();
	
	public Date getDate() {
		return date.getTime();
	}

	public void setDate(Date date) {
		this.date.setTime(date);
	}
	
	public List<PlayerAvailability> getPlayers(TimeFrameLocator locator) {
		List<PlayerAvailability> pa = players.get(locator);
		if (pa == null) {
			pa = new ArrayList<PlayerAvailability>();
			players.put(locator, pa);
		}
		return pa;
	}
	
	public List<PlayerAvailability> getAfternoonPlayers() {
		return getPlayers(TimeFrameLocator.AFTERNOON);
	}

	public List<PlayerAvailability> getEveningPlayers() {
		return getPlayers(TimeFrameLocator.EVENING);
	}

	public void addGame(Game game) {
		this.games.add(game);
	}
	
	public void addPlayerAvailability(PlayerAvailability pa) {
		getPlayers(pa.getTimeFrame().getLocator()).add(pa);
	}
	
	public List<Game> getGames() {
		return games;
	}

	public TimeFrame getAfternoonTimeFrame() {
		return new TimeFrame(getDate(), TimeFrameLocator.AFTERNOON);
	}
	
	public TimeFrame getEveningTimeFrame() {
		return new TimeFrame(getDate(), TimeFrameLocator.EVENING);
	}
	
	public List<Game> getAfternoonGames() {
		return getGames(TimeFrameLocator.AFTERNOON);
	}
	
	public List<Game> getEveningGames() {
		return getGames(TimeFrameLocator.EVENING);
	}
	
	private List<Setting> getDistinctSettings(List<PlayerAvailability> players) {
		Set<Setting> settings = new HashSet<Setting>();
		for(PlayerAvailability pa : players) {
			settings.add(pa.getSetting());
		}
		return new ArrayList<Setting>(settings);
	}
	
	public List<Setting> getAfternoonSettings() {
		return getDistinctSettings(getPlayers(TimeFrameLocator.AFTERNOON));
	}
	
	public List<Setting> getEveningSettings() {
		return getDistinctSettings(getPlayers(TimeFrameLocator.EVENING));
	}
	
	private List<Game> getGames(TimeFrameLocator locator) {
		List<Game> result = new ArrayList<Game>();
		for(Game game : games) {
			if (locator.equals(game.getTimeFrame().getLocator())) {
				result.add(game);
			}
		}
		return result;
	}

	public String getDayLetter(Locale locale) {
		return date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale).substring(0,  1).toUpperCase();
	}
	
	public String getDayLetter() {
		return getDayLetter(FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}
	
	public int getDayNumber() {
		return date.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getMonthNumber() {
		return date.get(Calendar.MONTH);
	}
	
	public boolean isPlayDay() {
		return PLAY_DAYS.contains(date.get(Calendar.DAY_OF_WEEK));
	}
	
	public Day(Date date) {
		setDate(date);
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public void clearPlayerAvailabilities() {
		players.clear();		
	}
	
}
