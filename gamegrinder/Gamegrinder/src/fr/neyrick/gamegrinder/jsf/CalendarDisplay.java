package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.GameManager;
import fr.neyrick.gamegrinder.dao.NotesManager;
import fr.neyrick.gamegrinder.entities.Day;
import fr.neyrick.gamegrinder.entities.Note;
import fr.neyrick.gamegrinder.entities.PlayerAvailability;
import fr.neyrick.gamegrinder.entities.Setting;
import fr.neyrick.gamegrinder.entities.TimeFrame;

@Named("calendarDisplay")
@SessionScoped
public class CalendarDisplay implements Serializable {

	public static final String EDIT_BLOCK_MODE_EMPTY = "EMPTY";
	public static final String EDIT_BLOCK_MODE_NOTES = "NOTES";
	public static final String EDIT_BLOCK_MODE_GAMES = "GAMES";
	
//	public static final String TF_BUTTON_MODE_OFF = "OFF";
//	public static final String TF_BUTTON_MODE_UPDATE = "UPDATE";
//	public static final String TF_BUTTON_MODE_SUB = "CLEAR";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static private final int DEFAULT_WIDTH = 2;
	
	private Map<Date, List<Day>> months = new TreeMap<Date, List<Day>>();

	private Map<TimeFrame, Set<Setting>> userAvails = new HashMap<TimeFrame, Set<Setting>>();
	
	private List<SelectItem> availablePlayers = null;

	private Date startDate, endDate, calendarStartDate; 
	
	private int width = DEFAULT_WIDTH;
	
	private String editBlockMode = EDIT_BLOCK_MODE_EMPTY;
	
	@Inject
	private Instance<GameManager> gameManagerInstance;
	
	@Inject
	private Instance<NotesManager> notesManagerInstance;	
	
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
				day.clearGames();
				day.getNotes().clear();
				day.clearPlayerAvailabilities();
			}
		}
		userAvails.clear();
		
		List<PlayerAvailability> avails = gameManagerInstance.get().fetchPlayers(calendarStartDate, endDate);

		Set<Setting> userSettings = null;
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
								userSettings = new HashSet<Setting>();
								userAvails.put(pa.getTimeFrame(), userSettings);								
							}
							userSettings.add(pa.getSetting());
						}
					}
				}
			}
		}

		List<Note> notes = notesManagerInstance.get().fetchNotes(calendarStartDate, endDate);
		Calendar noteCal = Calendar.getInstance();
		Date noteDate = null;
		for (Note note : notes) {
			if (note.getPostDate().before(calendarStartDate)) break;
			if (note.getPostDate().after(endDate)) break;
			noteCal.setTime(note.getPostDate());
			noteCal.set(Calendar.HOUR_OF_DAY, 0);
			noteCal.set(Calendar.MINUTE, 0);
			noteCal.set(Calendar.SECOND, 0);
			noteCal.set(Calendar.MILLISECOND, 0);
			noteDate = noteCal.getTime();
			noteCal.set(Calendar.DAY_OF_MONTH, 1);
			for (Day day : months.get(noteCal.getTime())) {
				if (day.getDate().equals(noteDate)) {
					day.addNote(note);
					break;
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
	
	
	public Map<TimeFrame, Set<Setting>> getUserAvails() {
		return userAvails;
	}

	public void setStartDate(Date startDate) {
		this.startDate = endDate;
		computeEndDate();
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
		Set<Setting> userList = userAvails.get(timeFrame);
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
//			if ((currentDay != null) && (iterDay.getDate().equals(currentDay.getDate()))) styleClass = "selectedDay";
			if (iterDay.getDate().before(startDate)) styleClass = "greyedDay"; 
			else if (iterDay.isPlayDay()) styleClass = "emptyDay";
			else styleClass = "notPlayDay";
			builder.append(styleClass);
			builder.append(", ");
		}
		return builder.toString();
	}
	
	public void clearAvailablePlayers() {
		availablePlayers = null;
	}
	
	public List<SelectItem> getAvailablePlayersForCurrentEdit() {
		if (availablePlayers == null) {
			availablePlayers = new ArrayList<SelectItem>();
			for(PlayerAvailability pa : planningUpdater.getCurrentDay().getPlayers(planningUpdater.getCurrentTimeFrame().getLocator())) {
				if (pa.getSetting().equals(planningUpdater.getCurrentDetailSetting()) && (pa.getGame() == null)) {
					availablePlayers.add(new SelectItem(pa.getId(), pa.getPlayerName()));
				}
			}
		}
		return availablePlayers;
	}
	
/*	
	public void saveDay() {
		editMode = false;
		currentDay = null;
	}
	
	public boolean isEditDayMode() {
		return editMode;
	}
	*/
	/*
	public String getButtonMode(TimeFrame timeframe) {
		if (userAvails.containsKey(timeframe)) return TF_BUTTON_MODE_SUB;
		if (planningUpdater.getCurrentSettings().size() == 0) return TF_BUTTON_MODE_OFF;
		return TF_BUTTON_MODE_ADD;
	}*/
}
