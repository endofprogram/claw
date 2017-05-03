package org.eop.claw.node.element;

import java.util.Map;

import org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class ObjectElement extends ElementNode {

	public ObjectElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, Object.class);
	}
}
