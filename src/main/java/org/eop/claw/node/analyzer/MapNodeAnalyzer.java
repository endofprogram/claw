package org.eop.claw.node.analyzer;

import org.eop.claw.node.WNode;
import org.eop.claw.node.WNodeAnalyzer;
import org.eop.claw.node.navi.MapNode;
/**
 * lixinjie 2016-12-26
 */
public class MapNodeAnalyzer extends WNodeAnalyzer {

	public MapNodeAnalyzer() {
		super("{", "}");
	}
	
	@Override
	protected WNode buildWnode(String segment) {
		return new MapNode(wnodeName, wnodeType, wnodeSetting);
	}

}
