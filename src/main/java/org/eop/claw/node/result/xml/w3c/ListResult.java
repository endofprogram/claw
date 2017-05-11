package org.eop.claw.node.result.xml.w3c;

import java.util.ArrayList;
import java.util.List;

import org.eop.claw.node.navi.index.SingleIndex;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
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
	protected AbstractObjectResult getObjectResult(SingleIndex singleIndex) {
		return new W3cElementResult((Element)objects.get(singleIndex.getIndex()));
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@Override
	protected Object getValueByName(Object object, String name) {
		Element element = (Element)object;
		if ("text".equals(name)) {
			return element.getTextContent();
		}
		String attrValue = element.getAttribute(name);
		if (attrValue != null && !attrValue.isEmpty()) {
			return attrValue;
		}
		return getChildrenWithTagName(element, name);
	}

	protected Object getChildrenWithTagName(Element element, String tagName) {
		List<Object> children = new ArrayList<>();
		NodeList nl = element.getChildNodes();
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0, len = nl.getLength(); i < len; i++) {
				if (nl.item(i) instanceof Element) {
					if (nl.item(i).getNodeName().equals(tagName)) {
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
