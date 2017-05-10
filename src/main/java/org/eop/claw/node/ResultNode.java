package org.eop.claw.node;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public interface ResultNode {

	String getType();
	void setType(String type);
	
	ResultNode getResult(NaviNode naviNode);
	
	Object getValue();
}
