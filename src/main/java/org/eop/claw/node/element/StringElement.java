package org.eop.claw.node.element;

import java.util.Map;

import org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class StringElement extends ElementNode {

	public StringElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, String.class);
	}
}
