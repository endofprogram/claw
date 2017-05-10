package org.eop.claw.internal.map;

import java.util.Map;

import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;

import old.org.eop.claw.node.RNode;
import old.org.eop.claw.node.result.map.MapResult;
/**
 * lixinjie 2016-12-26
 */
public class MapClaw extends AbstractClaw {

	public MapClaw(Map<String, Object> map) {
		this(new MapResult(map));
	}
	
	protected MapClaw(RNode rootRNode) {
		super(rootRNode);
	}

	@Override
	public IClaw getClaw(String path) {
		crawlRNode(path);
		return new MapClaw(currentRNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}

}
