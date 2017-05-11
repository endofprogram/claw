package org.eop.claw.node.result.xml.dom4j;

import java.util.List;

import org.dom4j.Element;
import org.eop.claw.node.navi.index.SingleIndex;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;

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
		return new Dom4jElementResult((Element)objects.get(singleIndex.getIndex()));
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@Override
	protected Object getValueByName(Object object, String name) {
		Element element = (Element)object;
		if ("text".equals(name)) {
			return element.getTextTrim();
		}
		String attrValue = element.attributeValue(name);
		if (attrValue != null && !attrValue.isEmpty()) {
			return attrValue;
		}
		return getChildrenWithTagName(element, name);
	}

	@SuppressWarnings("unchecked")
	protected Object getChildrenWithTagName(Element element, String tagName) {
		List<Object> children = element.elements(tagName);
		if (children != null && !children.isEmpty()) {
			if (children.size() < 2) {
				return children.get(0);
			}
			return children;
		}
		return null;
	}
}
