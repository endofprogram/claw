package org.eop.claw.node.result.xml.w3c;

import org.eop.chassis.util.TypeUtil;
import org.eop.claw.node.ElementNode;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.navi.ListNode;
import org.eop.claw.node.navi.MapNode;
import org.eop.claw.node.result.ElementListListResult;
import org.eop.claw.node.result.ElementListResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.util.W3cXmlUtil;
import org.w3c.dom.Element;
/**
 * lixinjie 2016-12-26
 */
public class W3cElementResult extends RNode {

	protected Element element;
	
	public W3cElementResult(Element element) {
		super("{}");
		this.element = element;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof ElementNode) {
			return new ElementResult(W3cXmlUtil.getText(W3cXmlUtil.getFirstChild(element, wnode.getName()), true, ""));
		} else if (wnode instanceof ListNode) {
			if (isListListList(wnode.getName())) {
				return new ListListListResult(W3cXmlUtil.getListListList(element, wnode.getName()));
			}
			if (isElementListList(wnode.getName())) {
				return new ElementListListResult(W3cXmlUtil.getElementListList(element, wnode.getName()));
			}
			if (isW3cElementListList(wnode.getName())) {
				return new W3cElementListListResult(W3cXmlUtil.getW3cElementListList(element, wnode.getName()));
			}
			if (isW3cElementList(wnode.getName())) {
				return new W3cElementListResult(W3cXmlUtil.getChildren(W3cXmlUtil.getFirstChild(element, wnode.getName())));
			}
			return new ElementListResult(TypeUtil.asListType(W3cXmlUtil.getChildrenText(W3cXmlUtil.getFirstChild(element, wnode.getName()))));
		} else if (wnode instanceof MapNode) {
			return new W3cElementResult(W3cXmlUtil.getFirstChild(element, wnode.getName()));
		}
		return null;
	}

	@Override
	public Element getValue() {
		return element;
	}
	
	protected boolean isW3cElementList(String tagName) {
		return W3cXmlUtil.getW3cElementList(element, tagName) != null;
	}
	
	protected boolean isW3cElementListList(String tagName) {
		return W3cXmlUtil.getW3cElementListList(element, tagName) != null;
	}
	
	protected boolean isElementListList(String tagName) {
		return W3cXmlUtil.getElementListList(element, tagName) != null;
	}
	
	protected boolean isListListList(String tagName) {
		return W3cXmlUtil.getListListList(element, tagName) != null;
	}
}
