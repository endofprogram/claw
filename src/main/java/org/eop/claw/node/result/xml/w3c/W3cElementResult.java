package org.eop.claw.node.result.xml.w3c;

import java.util.ArrayList;
import java.util.List;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.node.result.exception.ResultNodeException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class W3cElementResult extends AbstractObjectResult {

	public W3cElementResult(Element element) {
		super(element);
	}

	@Override
	protected AbstractObjectResult getObjectResult(NameNode nameNode) {
		Element element = getFirstChildWithTagName((Element)object, nameNode.getName());
		if (element != null) {
			return new W3cElementResult(element);
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		if (!getChildrenWithTagName((Element)object, nameNode.getName()).isEmpty()) {
			return new ListResult(getChildrenWithTagName((Element)object, nameNode.getName()));
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		if ("text".equals(nameNode.getName())) {
			return new ElementResult(((Element)object).getTextContent());
		}
		if (((Element)object).getAttributeNode(nameNode.getName()) != null) {
			return new ElementResult(((Element)object).getAttributeNode(nameNode.getName()).getValue());
		}
		throw new ResultNodeException("not contains a attribute with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

	protected Element getFirstChildWithTagName(Element element, String tagName) {
		NodeList nl = element.getChildNodes();
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0, len = nl.getLength(); i < len; i++) {
				if (nl.item(i) instanceof Element) {
					if (nl.item(i).getNodeName().equals(tagName)) {
						return (Element)nl.item(i);
					}
				}
			}
		}
		return null;
	}
	
	protected List<Object> getChildrenWithTagName(Element element, String tagName) {
		List<Object> objectList = new ArrayList<>();
		NodeList nl = element.getChildNodes();
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0, len = nl.getLength(); i < len; i++) {
				if (nl.item(i) instanceof Element) {
					if (nl.item(i).getNodeName().equals(tagName)) {
						objectList.add(nl.item(i));
					}
				}
			}
		}
		return objectList;
	}
}
