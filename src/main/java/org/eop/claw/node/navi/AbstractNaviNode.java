package org.eop.claw.node.navi;

import org.eop.claw.node.NaviNode;
import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class AbstractNaviNode implements NaviNode {

	protected String segment;
	protected String name;
	protected int depth;
	protected ResultType resultType;
	protected NodeSetting nodeSetting;
	
	protected AbstractNaviNode(String segment, String name, int depth, ResultType resultType, NodeSetting nodeSetting) {
		this.segment = segment;
		this.name = name;
		this.depth = depth;
		this.resultType =resultType;
		this.nodeSetting = nodeSetting;
	}
	
	@Override
	public String getSegment() {
		return segment;
	}
	
	@Override
	public void setSegment(String segment) {
		this.segment = segment;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public ResultType getResultType() {
		return resultType;
	}

	@Override
	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	@Override
	public NodeSetting getNodeSetting() {
		return nodeSetting;
	}

	@Override
	public void setNodeSettings(NodeSetting nodeSetting) {
		this.nodeSetting = nodeSetting;
	}

}
