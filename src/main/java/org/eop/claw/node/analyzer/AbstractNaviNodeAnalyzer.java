package org.eop.claw.node.analyzer;

import org.eop.claw.node.NaviNode;
import org.eop.claw.node.NaviNodeAnalyzer;
import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class AbstractNaviNodeAnalyzer implements NaviNodeAnalyzer {

	protected String indexFlag;
	protected String depthFlag;
	
	protected AbstractNaviNodeAnalyzer(String indexFlag, String depthFlag) {
		this.indexFlag = indexFlag;
		this.depthFlag = depthFlag;
	}
	
	@Override
	public String getIndexFlag() {
		return indexFlag;
	}

	@Override
	public void setIndexFlag(String indexFlag) {
		this.indexFlag = indexFlag;
	}

	@Override
	public String getDepthFlag() {
		return depthFlag;
	}

	@Override
	public void setDepthFlag(String depthFlag) {
		this.depthFlag = depthFlag;
	}

	@Override
	public NaviNode analyze(String segment) {
		String name = analyzeName(segment);
		int depth = analyzeDepth(segment);
		ResultType resultType = analyzeResultType(segment);
		NodeSetting nodeSetting = analyzeNodeSetting(segment);
		return buildNaviNode(name, depth, resultType, nodeSetting, segment);
	}

	protected String analyzeName(String segment) {
		return null;
	}
	
	protected int analyzeDepth(String segment) {
		return 0;
	}
	
	protected ResultType analyzeResultType(String segment) {
		return null;
	}
	
	protected NodeSetting analyzeNodeSetting(String segment) {
		return null;
	}
	
	protected abstract NaviNode buildNaviNode(String name, int depth, ResultType resultType, NodeSetting nodeSetting, String segment);
}
