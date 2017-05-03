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
public class ElementListResult extends RNode {

	protected List<Object> elementList;
	
	public ElementListResult(List<Object> elementList) {
		super("[<>]");
		this.elementList = elementList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof SingleIndex) {
			return new ElementResult(ListUtil.getAt(elementList, ((SingleIndex)wnode).getIndex()));
		} else if (wnode instanceof RangeIndex) {
			return new ElementListResult(ListUtil.getRange(elementList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
		} else if (wnode instanceof DisperIndex) {
			return new ElementListResult(ListUtil.getDisper(elementList, ((DisperIndex)wnode).getIndexes()));
		}
		return null;
	}

	@Override
	public List<Object> getValue() {
		return elementList;
	}
}
