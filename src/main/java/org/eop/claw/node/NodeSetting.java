package org.eop.claw.node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class NodeSetting {
	
	private Map<String, String> settings;
	
	public NodeSetting() {
		settings = new HashMap<>();
	}

	public void addSetting(String name, String value) {
		settings.put(name, value);
	}
	
	public String getSetting(String name) {
		return settings.get(name);
	}
}
