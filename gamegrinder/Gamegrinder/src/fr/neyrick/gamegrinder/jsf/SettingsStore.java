package fr.neyrick.gamegrinder.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.neyrick.gamegrinder.dao.SettingsManager;
import fr.neyrick.gamegrinder.entities.Setting;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class SettingsStore implements Serializable {

	private Map<Long, Setting> settings = new TreeMap<Long, Setting>();
	
	@Inject
	private SettingsManager settingsManager;
	
	public void refreshMap() {
		settings.clear();
		for(Setting setting : settingsManager.fetchSettings()) {
			settings.put(setting.getId(), setting);
		}		
	}
	
	
	
	@PostConstruct
	private void init() {
		refreshMap();
	}
	
	public Setting getSetting(Long id) {
		return settings.get(id);
	}
	
	public List<Setting> getOpenSettings() {
		return getSettings(true);
	}
	
	public List<Setting> getClosedSettings() {
		return getSettings(false);
	}
		
	private List<Setting> getSettings(boolean open) {
		List<Setting> result = new ArrayList<Setting>();
		for(Setting setting : settings.values()) {
			if (setting.isOpen() == open) result.add(setting);
		}
		return result;
	}
	
	public List<Setting> getSettings(){
		return new ArrayList<Setting>(settings.values());
	}
	
	public void createSetting(Setting setting) {
		settingsManager.storeSetting(setting);
	}
	
	public SettingsStore() {
	}

}
