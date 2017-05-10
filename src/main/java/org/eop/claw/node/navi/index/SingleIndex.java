package org.eop.claw.node.navi.index;

import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.IndexNode;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class SingleIndex extends IndexNode {

	public SingleIndex(String segment, String name, int depth, ResultType resultType, NodeSetting nodeSetting, Integer index) {
		super(segment, name, depth, resultType, nodeSetting);
		this.indexes.add(index);
	}
	
	public Integer getIndex() {
		return indexes.get(0);
	}

}
