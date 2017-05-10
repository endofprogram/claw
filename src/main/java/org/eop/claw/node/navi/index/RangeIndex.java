package org.eop.claw.node.navi.index;

import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.IndexNode;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class RangeIndex extends IndexNode {

	public RangeIndex(String segment, String name, int depth, ResultType resultType, NodeSetting nodeSetting, Integer beginIndex, Integer endIndex) {
		super(segment, name, depth, resultType, nodeSetting);
		this.indexes.add(beginIndex);
		this.indexes.add(endIndex);
	}
	
	public Integer getBeginIndex() {
		return indexes.get(0);
	}
	
	public Integer getEndIndex() {
		return indexes.get(1);
	}

}
