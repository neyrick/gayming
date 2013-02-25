package fr.neyrick.gamegrinder.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.SettingsManager;
import fr.neyrick.gamegrinder.entities.Setting;

@Named
@RequestScoped
public class SettingsDisplay {

	private static final String STYLE_CLEAR_ROW = "settingsRow";
	private static final String STYLE_SELECTED_ROW = "settingsSelectedRow";
	
	@Inject
	private SettingsManager manager;

	@Inject
	private PlanningUpdater planningUpdater;
	
	private List<Setting> openSettings;
	
	private List<Setting> closedSettings;
	
	@PostConstruct
	private void init() {
		openSettings = manager.fetchSettings(true);
		closedSettings = manager.fetchSettings(false);
	}
	
	public List<Setting> getOpenSettings() {
		return openSettings;
	}
	
	public List<Setting> getClosedSettings() {
		return closedSettings;
	}
	
	public String getStyle(Setting setting) {
		return "background-color: " + setting.getColor();
	}
	
	private String getRowClasses(List<Setting> settings) {
		StringBuilder builder = new StringBuilder();
		for(Setting setting : settings) {
			if(planningUpdater.isSettingActive(setting)) builder.append(STYLE_SELECTED_ROW);
			else builder.append(STYLE_CLEAR_ROW);
			builder.append(",");
		}
		return builder.toString();
	}
	
	public String getOpenRowClasses() {
		return getRowClasses(openSettings);
	}
	
	public String getClosedRowClasses() {
		return getRowClasses(closedSettings);
	}
	
	public SettingsDisplay() {
	}

}
