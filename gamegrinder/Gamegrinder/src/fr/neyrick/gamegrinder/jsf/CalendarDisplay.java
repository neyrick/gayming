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
import fr.neyrick.gamegrinder.entities.PlayerAvailability;
import fr.neyrick.gamegrinder.entities.Setting;
import fr.neyrick.gamegrinder.entities.TimeFrame;

@Named("calendarDisplay")
@SessionScoped
public class CalendarDisplay implements Serializable {

	public static final String EDIT_BLOCK_MODE_EMPTY = "EMPTY";
	public static final String EDIT_BLOCK_MODE_NOTES = "NOTES";
	public static final String EDIT_BLOCK_MODE_GAMES = "GAMES";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static private final int DEFAULT_WIDTH = 2;
	
	private Map<Date, List<Day>> months = new HashMap<Date, List<Day>>();

	private Map<TimeFrame, List<Setting>> userAvails = new HashMap<TimeFrame, List<Setting>>();

	private Date startDate, endDate, calendarStartDate; 
	
	private int width = DEFAULT_WIDTH;
	
	private boolean editMode = false;
	
	private Day currentDay = null;
	
	private String editBlockMode = EDIT_BLOCK_MODE_EMPTY;
	
	@Inject
	private Instance<GameManager> gameManagerInstance;
	
	@Inject
	private Visitor visitor;
	
	@Inject
	private PlanningUpdater planningUpdater;
	
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
				day.clearPlayerAvailabilities();
			}
		}
		userAvails.clear();
		
		List<PlayerAvailability> avails = gameManagerInstance.get().fetchPlayers(calendarStartDate, endDate);

		List<Setting> userSettings = null;
		for (List<Day> dayList : months.values()) {
			for (Day day : dayList) {
				for(PlayerAvailability pa : avails) {
					if (day.getDate().equals(pa.getTimeFrame().getDayDate())) {
						day.addPlayerAvailability(pa);
						if (pa.getGame() != null) {
							day.addGame(pa.getGame());
						}
						if (pa.getPlayerName().equals(visitor.getName())) {
							userSettings = userAvails.get(pa.getTimeFrame());
							if (userSettings == null) {
								userSettings = new ArrayList<Setting>();
								userAvails.put(pa.getTimeFrame(), userSettings);								
							}
							userSettings.add(pa.getSetting());
						}
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
		calendarStartDate = currentCal.getTime();
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
	
	public Day getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(Day currentDay) {
		editMode = true;
		this.currentDay = currentDay;
	}

	public String getEditBlockMode() {
		return editBlockMode;
	}

	public void setEditBlockMode(String editBlockMode) {
		this.editBlockMode = editBlockMode;
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
	
	public boolean isAvailable(TimeFrame timeFrame, Setting setting) {
		List<Setting> userList = userAvails.get(timeFrame);
		if (userList == null) return false;
		return userList.contains(setting);
	}
	
	public List<PlayerAvailability> getFilteredList(List<PlayerAvailability> sourceList, Setting setting) {
		List<PlayerAvailability> result = new ArrayList<PlayerAvailability>();
		for (PlayerAvailability pa : sourceList) {
			if (pa.getSetting().equals(setting)) {
				result.add(pa);
			}
		}
		return result;
	}
	
	public String getMonthStyleClasses(Date monthStart) {
		List<Day> days = months.get(monthStart);
		if (days == null) return "";
		StringBuilder builder = new StringBuilder(255);
		String styleClass = null;
		Iterator<Day> iter = days.iterator();
		Day iterDay = null;
		while (iter.hasNext()) {
			iterDay = iter.next();
			if ((currentDay != null) && (iterDay.getDate().equals(currentDay.getDate()))) styleClass = "selectedDay";
			else if (iterDay.getDate().before(startDate)) styleClass = "greyedDay"; 
			else if (iterDay.isPlayDay()) styleClass = "emptyDay";
			else styleClass = "notPlayDay";
			builder.append(styleClass);
			builder.append(", ");
		}
		return builder.toString();
	}
	
	public void saveDay() {
		editMode = false;
		currentDay = null;
	}
	
	public boolean isEditDayMode() {
		return editMode;
	}
}
