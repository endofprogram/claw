package org.eop.claw.node;

import java.util.Map;
/**
 * lixinjie 2016-12-26
 */
public class ElementNode extends WNode {

	public ElementNode(String name, String type, Map<String, String> setting, Class<?> elementType) {
		super(name, type, setting);
		this.elementType = elementType;
	}
	
}
