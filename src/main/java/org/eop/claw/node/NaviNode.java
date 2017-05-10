package org.eop.claw.node;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public interface NaviNode {

	String getName();
	void setName(String name);
	
	int getDepth();
	void setDepth(int depth);
	
	ResultType getResultType();
	void setResultType(ResultType resultType);
	
	NodeSetting getNodeSetting();
	void setNodeSettings(NodeSetting nodeSetting);
	void addSetting(String name, String value);
	String getSetting(String name);
}
