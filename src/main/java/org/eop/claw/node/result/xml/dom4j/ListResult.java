package org.eop.claw.node.result.xml.dom4j;

import java.util.List;

import org.dom4j.Element;
import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class ListResult extends AbstractListResult {

	public ListResult(List<Object> objects) {
		super(objects);
	}

	@Override
	protected AbstractObjectResult getObjectResult(Object object) {
		return new Dom4jElementResult((Element)object);
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@Override
	protected Object getValueByName(Object object, NameNode nameNode) {
		Element element = (Element)object;
		if ("text".equals(nameNode.getName())) {
			return element.getTextTrim();
		}
		String attrValue = element.attributeValue(nameNode.getName());
		if (attrValue != null) {
			return attrValue;
		}
		Object children = getChildrenWithTagName(element, nameNode);
		if (children != null) {
			return children;
		}
		throw new ResultNodeException("not contains a subnode or attribute with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	@SuppressWarnings("unchecked")
	protected Object getChildrenWithTagName(Element element, NameNode nameNode) {
		List<Object> children = element.elements(nameNode.getName());
		if (!children.isEmpty()) {
			if (children.size() < 2) {
				return children.get(0);
			}
			return children;
		}
		return null;
	}
}
