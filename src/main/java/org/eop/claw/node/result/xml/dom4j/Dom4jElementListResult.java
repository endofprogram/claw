package org.eop.claw.node.result.xml.dom4j;

import java.util.List;

import org.dom4j.Element;
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
import org.eop.claw.util.Dom4jXmlUtil;
/**
 * lixinjie 2016-12-26
 */
public class Dom4jElementListResult extends RNode {

	protected List<Element> elementList;
	
	public Dom4jElementListResult(List<Element> elementList) {
		super("[{}]");
		this.elementList = elementList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof IndexNode) {
			if (wnode instanceof SingleIndex) {
				return new Dom4jElementResult(ListUtil.getAt(elementList, ((SingleIndex)wnode).getIndex()));
			} else if (wnode instanceof RangeIndex) {
				return new Dom4jElementListResult(ListUtil.getRange(elementList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
			} else if (wnode instanceof DisperIndex) {
				return new Dom4jElementListResult(ListUtil.getDisper(elementList, ((DisperIndex)wnode).getIndexes()));
			}
			return null;
		} else if (wnode instanceof ElementNode) {
			return new ElementListResult(TypeUtil.asListType(Dom4jXmlUtil.getAllFirstChild(elementList, wnode.getName())));
		} else if (wnode instanceof MapNode) {
			return new Dom4jElementListResult(Dom4jXmlUtil.getAllFirstChild(elementList, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isElementListList(wnode.getName())) {
				return new Dom4jElementListListResult(Dom4jXmlUtil.getAllChildren(elementList, wnode.getName()));
			}
			return new ElementListListResult(TypeUtil.asListListType(Dom4jXmlUtil.getAllChildrenText(elementList, wnode.getName())));
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
		Element elem = Dom4jXmlUtil.getFirstChild(ListUtil.getFirst(elementList), tagName);
		elem = Dom4jXmlUtil.getFirstChild(elem);
		elem = Dom4jXmlUtil.getFirstChild(elem);
		return elem != null;
	}
}
