package org.eop.claw.internal.map;

import java.util.Map;

import org.eop.claw.AbstractClaw;
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
	
	protected MapClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}

	@Override
	protected IClaw getClaw() {
		return new MapClaw(currentResultNode);
	}

}
