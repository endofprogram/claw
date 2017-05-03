package org.eop.claw.node.result.map;

import java.util.List;
import java.util.Map;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.MapUtil;
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
/**
 * lixinjie 2016-12-26
 */
public class MapListResult extends RNode {

	protected List<Map<String, Object>> mapList;
	
	public MapListResult(List<Map<String, Object>> mapList) {
		super("[{}]");
		this.mapList = mapList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof IndexNode) {
			if (wnode instanceof SingleIndex) {
				return new MapResult(ListUtil.getItem(mapList, ((SingleIndex)wnode).getIndex()));
			} else if (wnode instanceof RangeIndex) {
				return new MapListResult(ListUtil.getList(mapList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
			} else if (wnode instanceof DisperIndex) {
				return new MapListResult(ListUtil.getList(mapList, ((DisperIndex)wnode).getIndexes()));
			}
			return null;
		} else if (wnode instanceof ElementNode) {
			return new ElementListResult(MapUtil.getAllValue(mapList, wnode.getName()));
		} else if (wnode instanceof MapNode) {
			return new MapListResult(MapUtil.getAllMap(mapList, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isMapListList(wnode.getName())) {
				return new MapListListResult(MapUtil.getAllMapList(mapList, wnode.getName()));
			}
			if (isListListList(wnode.getName())) {
				return new ListListListResult(MapUtil.getAllListList(mapList, wnode.getName()));
			}
			return new ElementListListResult(MapUtil.getAllValueList(mapList, wnode.getName()));
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getValue() {
		return mapList;
	}
	
	protected boolean isMapListList(String key) {
		if (EmptyUtil.isEmpty(mapList)) {
			return false;
		}
		return MapUtil.getMapList(ListUtil.getFirst(mapList), key) != null;
	}
	
	protected boolean isListListList(String key) {
		if (EmptyUtil.isEmpty(mapList)) {
			return false;
		}
		return MapUtil.getListList(ListUtil.getFirst(mapList), key) != null;
	}
}
