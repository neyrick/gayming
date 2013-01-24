package fr.neyrick.gamegrinder.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

public class Day {

	private Calendar date = Calendar.getInstance();
	
	private List<Game> games = new ArrayList<Game>();
	
	private static final List<Integer> PLAY_DAYS = Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY);

	public Date getDate() {
		return date.getTime();
	}

	public void setDate(Date date) {
		this.date.setTime(date);
	}

	public void addGame(Game game) {
		this.games.add(game);
	}
	
	public List<Game> getGames() {
		return games;
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
}
