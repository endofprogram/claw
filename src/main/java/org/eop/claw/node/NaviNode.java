package org.eop.claw.node;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public interface NaviNode {

	String getSegment();
	void setSegment(String segment);
	
	String getName();
	void setName(String name);
	
	int getDepth();
	void setDepth(int depth);
	
	ResultType getResultType();
	void setResultType(ResultType resultType);
	
	NodeSetting getNodeSetting();
	void setNodeSettings(NodeSetting nodeSetting);
	
}
