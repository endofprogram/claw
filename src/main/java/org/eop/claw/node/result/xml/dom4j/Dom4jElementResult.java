package org.eop.claw.node.result.xml.dom4j;

import org.dom4j.Element;
import org.eop.chassis.util.TypeUtil;
import org.eop.claw.node.ElementNode;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.navi.ListNode;
import org.eop.claw.node.navi.MapNode;
import org.eop.claw.node.result.ElementListResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.util.Dom4jXmlUtil;
/**
 * lixinjie 2016-12-26
 */
public class Dom4jElementResult extends RNode {

	protected Element element;
	
	public Dom4jElementResult(Element element) {
		super("{}");
		this.element = element;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof ElementNode) {
			return new ElementResult(Dom4jXmlUtil.getText(Dom4jXmlUtil.getFirstChild(element, wnode.getName()), true, ""));
		} else if (wnode instanceof ListNode) {
			if (isElementList(wnode.getName())) {
				return new Dom4jElementListResult(Dom4jXmlUtil.getChildren(Dom4jXmlUtil.getFirstChild(element, wnode.getName())));
			}
			return new ElementListResult(TypeUtil.asListType(Dom4jXmlUtil.getChildrenText(Dom4jXmlUtil.getFirstChild(element, wnode.getName()))));
		} else if (wnode instanceof MapNode) {
			return new Dom4jElementResult(Dom4jXmlUtil.getFirstChild(element, wnode.getName()));
		}
		return null;
	}

	@Override
	public Element getValue() {
		return element;
	}
	
	protected boolean isElementList(String tagName) {
		Element elem = Dom4jXmlUtil.getFirstChild(element, tagName);
		elem = Dom4jXmlUtil.getFirstChild(elem);
		elem = Dom4jXmlUtil.getFirstChild(elem);
		return elem != null;
	}
}
