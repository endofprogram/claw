package org.eop.claw.internal.map;

import java.util.Map;

import org.eop.claw.AbstractClaw;
import org.eop.claw.ClawSetting;
import org.eop.claw.IClaw;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.result.map.MapResult;
/**
 * lixinjie 2016-12-26
 */
public class MapClaw extends AbstractClaw {

	public MapClaw(Map<String, Object> map) {
		this(new MapResult(map));
	}
	
	public MapClaw(Map<String, Object> map, ClawSetting clawSetting) {
		this(new MapResult(map), clawSetting);
	}
	
	protected MapClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}
	
	protected MapClaw(ResultNode rootResultNode, ClawSetting clawSetting) {
		super(rootResultNode, clawSetting);
	}

	@Override
	protected IClaw getClaw() {
		return new MapClaw(currentResultNode, clawSetting);
	}

}
