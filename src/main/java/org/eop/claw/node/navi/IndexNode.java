package org.eop.claw.node.navi;

import java.util.ArrayList;
import java.util.List;

import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class IndexNode extends AbstractNaviNode {

	protected List<Integer> indexes = new ArrayList<>();
	
	protected IndexNode(String segment, String name, int depth, ResultType resultType, NodeSetting nodeSetting) {
		super(segment, name, depth, resultType, nodeSetting);
	}

}
