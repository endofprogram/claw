package org.eop.claw.node.result;

import org.eop.claw.node.NaviNode;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class ElementResult extends AbstractResultNode {

	protected Object element;
	
	public ElementResult(Object element) {
		super("<>");
		this.element = element;
	}

	@Override
	public ResultNode getResult(NaviNode naviNode) {
		throw new ResultNodeException("not allowed to navi any more at this point, segment '" + naviNode.getSegment() + "' is invalid");
	}

	@Override
	public Object getValue() {
		return element;
	}

}
