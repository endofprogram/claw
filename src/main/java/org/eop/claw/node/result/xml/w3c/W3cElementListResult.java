package org.eop.claw.node.result.xml.w3c;

import java.util.List;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;
import org.eop.claw.node.ElementNode;
import org.eop.claw.node.IndexNode;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.index.DisperIndex;
import org.eop.claw.node.index.RangeIndex;
import org.eop.claw.node.index.SingleIndex;
import org.eop.claw.node.navi.ListNode;
import org.eop.claw.node.navi.MapNode;
import org.eop.claw.node.result.ElementListListResult;
import org.eop.claw.node.result.ElementListResult;
import org.eop.claw.util.W3cXmlUtil;
import org.w3c.dom.Element;
/**
 * lixinjie 2016-12-26
 */
public class W3cElementListResult extends RNode {

	protected List<Element> elementList;
	
	public W3cElementListResult(List<Element> elementList) {
		super("[{}]");
		this.elementList = elementList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof IndexNode) {
			if (wnode instanceof SingleIndex) {
				return new W3cElementResult(ListUtil.getAt(elementList, ((SingleIndex)wnode).getIndex()));
			} else if (wnode instanceof RangeIndex) {
				return new W3cElementListResult(ListUtil.getRange(elementList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
			} else if (wnode instanceof DisperIndex) {
				return new W3cElementListResult(ListUtil.getDisper(elementList, ((DisperIndex)wnode).getIndexes()));
			}
			return null;
		} else if (wnode instanceof ElementNode) {
			return new ElementListResult(TypeUtil.asListType(W3cXmlUtil.getAllFirstChildText(elementList, wnode.getName())));
		} else if (wnode instanceof MapNode) {
			return new W3cElementListResult(W3cXmlUtil.getAllFirstChild(elementList, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isListListList(wnode.getName())) {
				
			}
			if (isElementListList(wnode.getName())) {
				return new ElementListListResult(TypeUtil.asListListType(W3cXmlUtil.getAllChildrenText(elementList, wnode.getName())));
			}
			return new W3cElementListListResult(W3cXmlUtil.getAllChildren(elementList, wnode.getName()));
		}
		return null;
	}

	@Override
	public List<Element> getValue() {
		return elementList;
	}
	
	protected boolean isElementListList(String tagName) {
		if (EmptyUtil.isEmpty(elementList)) {
			return false;
		}
		Element elem = W3cXmlUtil.getFirstChild(ListUtil.getFirst(elementList), tagName);
		elem = W3cXmlUtil.getFirstChild(elem);
		elem = W3cXmlUtil.getFirstChild(elem);
		return elem != null;
	}
	
	protected boolean isListListList(String tagName) {
		if (EmptyUtil.isEmpty(elementList)) {
			return false;
		}
	}
}
