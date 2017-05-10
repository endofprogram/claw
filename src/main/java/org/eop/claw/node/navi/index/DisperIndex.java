package org.eop.claw.node.navi.index;

import java.util.List;

import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.IndexNode;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class DisperIndex extends IndexNode {

	public DisperIndex(String segment, String name, int depth, ResultType resultType, NodeSetting nodeSetting, List<Integer> indexes) {
		super(segment, name, depth, resultType, nodeSetting);
		this.indexes.addAll(indexes);
	}

	public List<Integer> getIndexes() {
		return indexes;
	}
	
}
