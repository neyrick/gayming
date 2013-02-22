package fr.neyrick.gamegrinder.jsf;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.SettingsManager;
import fr.neyrick.gamegrinder.entities.Setting;

@Named
@RequestScoped
public class SettingsDisplay {

	@Inject
	private SettingsManager manager;

	public List<Setting> getOpenSettings() {
		return manager.fetchSettings(true);
	}
	
	public List<Setting> getClosedSettings() {
		return manager.fetchSettings(false);
	}
	
	public String getStyle(Setting setting) {
		return "background-color: " + setting.getColor();
	}
	
	public SettingsDisplay() {
	}

}
