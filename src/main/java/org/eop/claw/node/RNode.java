package org.eop.claw.node;
/**
 * lixinjie 2016-12-26
 */
public abstract class RNode {

	protected String type;
	
	protected RNode(String type) {
		this.type = type;
	}
	
	public abstract RNode getResult(WNode wnode);
	
	public abstract Object getValue();
	
}
