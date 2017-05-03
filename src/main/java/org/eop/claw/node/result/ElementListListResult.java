package org.eop.claw.node.result;

import java.util.List;

import org.eop.chassis.util.ListUtil;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.index.DisperIndex;
import org.eop.claw.node.index.RangeIndex;
import org.eop.claw.node.index.SingleIndex;
/**
 * lixinjie 2016-12-26
 */
public class ElementListListResult extends RNode {

	protected List<List<Object>> elementListList;
	
	public ElementListListResult(List<List<Object>> elementListList) {
		super("[[<>]]");
		this.elementListList = elementListList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof SingleIndex) {
			return new ElementListResult(ListUtil.getAt(elementListList, ((SingleIndex)wnode).getIndex()));
		} else if (wnode instanceof RangeIndex) {
			return new ElementListListResult(ListUtil.getRange(elementListList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
		} else if (wnode instanceof DisperIndex) {
			return new ElementListListResult(ListUtil.getDisper(elementListList, ((DisperIndex)wnode).getIndexes()));
		}
		return null;
	}

	@Override
	public List<List<Object>> getValue() {
		return elementListList;
	}
}
