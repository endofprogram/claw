package org.eop.claw.node.result.xml.w3c;

import java.util.ArrayList;
import java.util.List;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.exception.ResultNodeException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
		return new W3cElementResult((Element)object);
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@Override
	protected Object getValueByName(Object object, NameNode nameNode) {
		Element element = (Element)object;
		if ("text".equals(nameNode.getName())) {
			return element.getTextContent();
		}
		Attr attr = element.getAttributeNode(nameNode.getName());
		if (attr != null) {
			return attr.getValue();
		}
		Object children = getChildrenWithTagName(element, nameNode);
		if (children != null) {
			return children;
		}
		throw new ResultNodeException("not contains a subnode or attribute with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	protected Object getChildrenWithTagName(Element element, NameNode nameNode) {
		List<Object> children = new ArrayList<>();
		NodeList nl = element.getChildNodes();
		if (nl.getLength() > 0) {
			for (int i = 0, len = nl.getLength(); i < len; i++) {
				if (nl.item(i) instanceof Element) {
					if (nl.item(i).getNodeName().equals(nameNode.getName())) {
						children.add(nl.item(i));
					}
				}
			}
		}
		if (!children.isEmpty()) {
			if (children.size() < 2) {
				return children.get(0);
			}
			return children;
		}
		return null;
	}
}
