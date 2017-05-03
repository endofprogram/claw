package org.eop.claw.node.result.map;

import java.util.List;
import java.util.Map;

import org.eop.chassis.util.ListUtil;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.index.DisperIndex;
import org.eop.claw.node.index.RangeIndex;
import org.eop.claw.node.index.SingleIndex;
/**
 * lixinjie 2016-12-26
 */
public class MapListListResult extends RNode {

	protected List<List<Map<String, Object>>> mapListList;
	
	public MapListListResult(List<List<Map<String, Object>>> mapListList) {
		super("[[{}]]");
		this.mapListList = mapListList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof SingleIndex) {
			return new MapListResult(ListUtil.getList(mapListList, ((SingleIndex)wnode).getIndex()));
		} else if (wnode instanceof RangeIndex) {
			return new MapListListResult(ListUtil.getListList(mapListList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
		} else if (wnode instanceof DisperIndex) {
			return new MapListListResult(ListUtil.getListList(mapListList, ((DisperIndex)wnode).getIndexes()));
		}
		return null;
	}

	@Override
	public List<List<Map<String, Object>>> getValue() {
		return mapListList;
	}
}
