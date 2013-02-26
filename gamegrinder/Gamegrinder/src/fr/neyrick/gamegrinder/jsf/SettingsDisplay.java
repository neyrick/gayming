package fr.neyrick.gamegrinder.jsf;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.entities.Setting;

@Named
@RequestScoped
public class SettingsDisplay {

	private static final String STYLE_CLEAR_ROW = "settingsRow";
	private static final String STYLE_SELECTED_ROW = "settingsSelectedRow";
	
	@Inject
	private PlanningUpdater planningUpdater;
	
	@Inject
	private SettingsStore settingsStore;
	
	private Map<Long, Setting> allSettings = new TreeMap<Long, Setting>();
	
	private String newSettingName;
	
	private String newSettingColor;
	
	private boolean newSettingOpen;
	
	public Setting getSetting(Long id) {
		return allSettings.get(id);
	}
	
	public List<Setting> getOpenSettings() {
		return settingsStore.getOpenSettings();
	}
	
	public List<Setting> getClosedSettings() {
		return settingsStore.getClosedSettings();
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
		return getRowClasses(getOpenSettings());
	}
	
	public String getClosedRowClasses() {
		return getRowClasses(getClosedSettings());
	}
	
	public String getNewSettingName() {
		return newSettingName;
	}

	public void setNewSettingName(String newSettingName) {
		this.newSettingName = newSettingName;
	}

	public String getNewSettingColor() {
		return newSettingColor;
	}

	public void setNewSettingColor(String newSettingColor) {
		this.newSettingColor = newSettingColor;
	}

	public boolean isNewSettingOpen() {
		return newSettingOpen;
	}

	public void setNewSettingOpen(boolean newSettingOpen) {
		this.newSettingOpen = newSettingOpen;
	}

	public void addNewSetting() {
		Setting setting = new Setting();
		setting.setName(newSettingName);
		setting.setColor(newSettingColor);
		setting.setOpen(newSettingOpen);
		settingsStore.createSetting(setting);
	}
	
	public SettingsDisplay() {
	}

}
