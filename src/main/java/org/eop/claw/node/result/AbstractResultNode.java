package org.eop.claw.node.result;

import org.eop.claw.node.NaviNode;
import org.eop.claw.node.ResultNode;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class AbstractResultNode implements ResultNode {

	protected String type;
	
	protected AbstractResultNode(String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public abstract ResultNode getResult(NaviNode naviNode);

	@Override
	public abstract Object getValue();

}
