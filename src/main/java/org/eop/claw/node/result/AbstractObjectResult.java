package org.eop.claw.node.result;

import org.eop.claw.node.NaviNode;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class AbstractObjectResult extends AbstractResultNode {

	protected Object object;
	
	protected AbstractObjectResult(Object object) {
		super("{}");
		this.object = object;
	}

	@Override
	public ResultNode getResult(NaviNode naviNode) {
		if (naviNode instanceof NameNode) {
			if (naviNode.getResultType() == ResultType.Object) {
				return getObjectResult((NameNode)naviNode);
			}
			if (naviNode.getResultType() == ResultType.List) {
				return getListResult((NameNode)naviNode);
			}
			if (naviNode.getResultType() == ResultType.Element) {
				return getElementResult((NameNode)naviNode);
			}
		}
		throw new ResultNodeException("only allowed to navi by name at this point, segment '" + naviNode.getSegment() + "' is invalid");
	}

	@Override
	public Object getValue() {
		return object;
	}

	protected abstract AbstractObjectResult getObjectResult(NameNode nameNode);
	
	protected abstract AbstractListResult getListResult(NameNode nameNode);
	
	protected abstract ElementResult getElementResult(NameNode nameNode);
	
}
