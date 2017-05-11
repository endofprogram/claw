package org.eop.claw.node.result.xml.dom4j;

import org.dom4j.Element;
import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;

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
		return new Dom4jElementResult(((Element)object).element(nameNode.getName()));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		return new ListResult(((Element)object).elements(nameNode.getName()));
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		if ("text".equals(nameNode.getName())) {
			return new ElementResult(((Element)object).getTextTrim());
		}
		return new ElementResult(((Element)object).attributeValue(nameNode.getName()));
	}

}
