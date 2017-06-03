package org.eop.claw;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class ClawSetting {
	
	private Map<String, String> settings;
	
	public ClawSetting() {
		settings = new HashMap<>();
		initDefaultSetting();
	}
	
	public ClawSetting(Map<String, String> settings) {
		this();
		initSetting(settings);
	}

	public void addSetting(String name, String value) {
		settings.put(name, value);
	}
	
	public String getSetting(String name) {
		return settings.get(name);
	}
	
	public Map<String, String> getSettings() {
		return settings;
	}
	
	protected void initDefaultSetting() {
		addSetting("index.flag", "#");
		addSetting("depth.flag", "!");
		addSetting("path.delimiter", ".");
	}
	
	protected void initSetting(Map<String, String> settings) {
		for (Map.Entry<String, String> entry : settings.entrySet()) {
			addSetting(entry.getKey(), entry.getValue());
		}
	}
}
