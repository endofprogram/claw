package org.eop.claw.node.result.xml.dom4j;

import org.dom4j.Element;
import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class Dom4jElementResult extends AbstractObjectResult {

	public Dom4jElementResult(Element element) {
		super(element);
	}

	@Override
	protected AbstractObjectResult getObjectResult(NameNode nameNode) {
		if (((Element)object).element(nameNode.getName()) != null) {
			return new Dom4jElementResult(((Element)object).element(nameNode.getName()));
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		if (!((Element)object).elements(nameNode.getName()).isEmpty()) {
			return new ListResult(((Element)object).elements(nameNode.getName()));
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		if ("text".equals(nameNode.getName())) {
			return new ElementResult(((Element)object).getTextTrim());
		}
		if (((Element)object).attributeValue(nameNode.getName()) != null) {
			return new ElementResult(((Element)object).attributeValue(nameNode.getName()));
		}
		throw new ResultNodeException("not contains a attribute with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

}
