package org.eop.claw.node;

import java.util.Map;

import org.eop.chassis.util.TypeUtil;
/**
 * lixinjie 2016-12-26
 */
public class NaviNode extends WNode {

	public NaviNode(String name, String type, Map<String, String> setting) {
		super(name, type, setting);
		this.elementType = TypeUtil.getType(setting.get("type"));
	}
}
