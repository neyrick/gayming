package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.entities.Setting;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class PlanningUpdater implements Serializable {

	private Setting currentSetting;

	@Inject
	private Visitor visitor;
	
	public Setting getCurrentSetting() {
		return currentSetting;
	}

	public void setCurrentSetting(Setting currentSetting) {
		this.currentSetting = currentSetting;
	}

	public PlanningUpdater() {
	}

}
