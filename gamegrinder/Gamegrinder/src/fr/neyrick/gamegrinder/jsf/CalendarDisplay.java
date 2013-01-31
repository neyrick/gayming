package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.GameManager;
import fr.neyrick.gamegrinder.entities.Day;
import fr.neyrick.gamegrinder.entities.Game;

@Named("calendarDisplay")
@SessionScoped
public class CalendarDisplay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static private final int DEFAULT_WIDTH = 3;
	
	private Map<Date, List<Day>> months = new HashMap<Date, List<Day>>();

	private Date startDate, endDate; 
	
	private int width = DEFAULT_WIDTH;
	
	private boolean editMode = false;
	
	private Date currentDate = null;
	
	@Inject
	private Instance<GameManager> gameManagerInstance;
	
	private void computeEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.MONTH, width);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		endDate = cal.getTime();
	}
	
	@PostConstruct
	private void init() {
		startDate = new Date();
		computeEndDate();
		resetDays();
	}
	
	public void loadGames() {
		for (List<Day> dayList : months.values()) {
			for (Day day : dayList) {
				day.getGames().clear();
			}
		}
		List<Game> games = gameManagerInstance.get().fetchGames(startDate, endDate);
		
		for (List<Day> dayList : months.values()) {
			for (Day day : dayList) {
				for(Game game : games) {
					if (day.getDate().equals(game.getTimeFrame().getDayDate())) {
						day.addGame(game);
					}
				}
			}
		}
	}
	
	private void resetDays() {
		months.clear();
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(startDate);
		currentCal.set(Calendar.DAY_OF_MONTH, 1);
		currentCal.set(Calendar.HOUR_OF_DAY, 0);
		currentCal.set(Calendar.MINUTE, 0);
		currentCal.set(Calendar.SECOND, 0);
		currentCal.set(Calendar.MILLISECOND, 0);
		List<Day> dayList;
		while(currentCal.getTime().before(endDate)) {
			int monthHeight = currentCal.getActualMaximum(Calendar.DAY_OF_MONTH);
			dayList = new ArrayList<Day>(monthHeight);
			months.put(currentCal.getTime(), dayList);
			for (int i=0; i<monthHeight; i++) {
				dayList.add(new Day(currentCal.getTime()));
				currentCal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = endDate;
		computeEndDate();
	}		
	
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		computeEndDate();
	}

	public Map<Date, List<Day>> getMonths() {
		return months;
	}

	public void setMonths(Map<Date, List<Day>> months) {
		this.months = months;
	}
	
	public List<Date> getMonthsList() {
		List<Date> monthsList = new ArrayList<Date>(width);
		monthsList.addAll(months.keySet());
		Collections.sort(monthsList);
		return monthsList;
	}
	
	public String getMonthStyleClasses(Date monthStart) {
		List<Day> days = months.get(monthStart);
		if (days == null) return "";
		StringBuilder builder = new StringBuilder(255);
		String styleClass = null;
		Iterator<Day> iter = days.iterator();
		Day currentDay = null;
		while (iter.hasNext()) {
			currentDay = iter.next();
			if (currentDay.getDate().equals(currentDate)) styleClass = "selectedDay";
			else if (currentDay.getDate().before(startDate)) styleClass = "greyedDay"; 
			else if (currentDay.isPlayDay()) styleClass = "emptyDay";
			else styleClass = "notPlayDay";
			builder.append(styleClass);
			builder.append(", ");
		}
		return builder.toString();
	}
	
	public String startEditDay() {
		editMode = true;
		return null;
	}
	
	public void saveDay() {
		editMode = false;
		currentDate = null;
	}
	
	public boolean isEditDayMode() {
		return editMode;
	}
}
