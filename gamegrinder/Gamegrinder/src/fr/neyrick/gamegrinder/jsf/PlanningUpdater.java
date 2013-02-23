package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;

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

	private Setting currentSetting;

	private TimeFrame currentTimeFrame;
	
	@Inject
	private Visitor visitor;
	
	@Inject
	private GameManager gameManager;
	
	public Setting getCurrentSetting() {
		return currentSetting;
	}

	public void setCurrentSetting(Setting currentSetting) {
		this.currentSetting = currentSetting;
	}

	public TimeFrame getCurrentTimeFrame() {
		return currentTimeFrame;
	}

	public void setCurrentTimeFrame(TimeFrame currentTimeFrame) {
		this.currentTimeFrame = currentTimeFrame;
	}

	public String addAvailability() {
		PlayerAvailability pa = new PlayerAvailability();
		pa.setPlayerName(visitor.getName());
		pa.setSetting(currentSetting);
		pa.setTimeFrame(currentTimeFrame);
		gameManager.storeAvailability(pa);
		return null;
	}
	
	public String removeAvailability() {
		gameManager.deleteAvailability(visitor.getName(), currentSetting, currentTimeFrame);
		return null;
	}
	
	public PlanningUpdater() {
	}

}
