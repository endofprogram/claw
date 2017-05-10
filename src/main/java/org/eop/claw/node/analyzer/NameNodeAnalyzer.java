package org.eop.claw.node.analyzer;

import org.eop.claw.node.NaviNode;
import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.NameNode;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class NameNodeAnalyzer extends AbstractNaviNodeAnalyzer {

	public NameNodeAnalyzer(String indexFlag, String depthFlag) {
		super(indexFlag, depthFlag);
	}

	@Override
	protected NaviNode buildNaviNode(String name, int depth, ResultType resultType, NodeSetting nodeSetting,
			String segment) {
		return new NameNode(segment, name, depth, resultType, nodeSetting);
	}

}
