package org.eop.claw.node.result;

import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
/**
 * lixinjie 2016-12-26
 */
public class ElementResult extends RNode {

	protected Object element;
	
	public ElementResult(Object element) {
		super("<>");
		this.element = element;
	}

	@Override
	public RNode getResult(WNode wnode) {
		return null;
	}

	@Override
	public Object getValue() {
		return element;
	}
}
