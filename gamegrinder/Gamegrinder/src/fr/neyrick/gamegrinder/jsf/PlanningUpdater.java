package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.GameManager;
import fr.neyrick.gamegrinder.entities.PlayerAvailability;
import fr.neyrick.gamegrinder.entities.Setting;
import fr.neyrick.gamegrinder.entities.TimeFrame;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class PlanningUpdater implements Serializable {

	private Set<Setting> currentSettings = new HashSet<Setting>();

	private TimeFrame currentTimeFrame;
	
	@Inject
	private Visitor visitor;
	
	@Inject
	private GameManager gameManager;
	
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

	public String addAvailability() {
		for (Setting setting : currentSettings) {
			PlayerAvailability pa = new PlayerAvailability();
			pa.setPlayerName(visitor.getName());
			pa.setSetting(setting);
			pa.setTimeFrame(currentTimeFrame);
			gameManager.storeAvailability(pa);
		}
		return null;
	}
	
	public PlanningUpdater() {
	}

}
