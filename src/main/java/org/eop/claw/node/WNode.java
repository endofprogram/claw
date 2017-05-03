package org.eop.claw.node;

import java.util.Map;
/**
 * lixinjie 2016-12-26
 */
public abstract class WNode {
	protected String name;
	protected String type;
	protected Map<String, String> setting;
	protected Class<?> elementType;
	
	protected WNode(String name, String type, Map<String, String> setting) {
		this.name = name;
		this.type = type;
		this.setting = setting;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, String> getSetting() {
		return setting;
	}
	
	public Class<?> getElementType() {
		return elementType;
	}
}
