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
	
	private List<String> events = new ArrayList<String>();
	
	private static final List<Integer> PLAY_DAYS = Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY);

	public Date getDate() {
		return date.getTime();
	}

	public void setDate(Date date) {
		this.date.setTime(date);
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}
	
	public void addEvent(String event) {
		this.events.add(event);
	}
	
	public String getDayLetter(Locale locale) {
		return date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale).substring(0,  1);
	}
	
	public String getDayLetter() {
		return getDayLetter(FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}
	
	public int getDayNumber() {
		return date.get(Calendar.DAY_OF_MONTH);
	}
	
	public boolean isPlayDay() {
		return PLAY_DAYS.contains(date.get(Calendar.DAY_OF_WEEK));
	}
	
	public Day(Date date) {
		setDate(date);
	}
}
