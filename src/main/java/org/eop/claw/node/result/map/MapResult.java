package org.eop.claw.node.result.map;

import java.util.Map;

import org.eop.chassis.util.MapUtil;
import org.eop.claw.node.ElementNode;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.navi.ListNode;
import org.eop.claw.node.navi.MapNode;
import org.eop.claw.node.result.ElementListListResult;
import org.eop.claw.node.result.ElementListResult;
import org.eop.claw.node.result.ElementResult;
/**
 * lixinjie 2016-12-26
 */
public class MapResult extends RNode {

	protected Map<String, Object> map;
	
	public MapResult(Map<String, Object> map) {
		super("{}");
		this.map = map;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof ElementNode) {
			return new ElementResult(MapUtil.getValue(map, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isMapList(wnode.getName())) {
				return new MapListResult(MapUtil.getMapList(map, wnode.getName()));
			}
			if (isMapListList(wnode.getName())) {
				return new MapListListResult(MapUtil.getMapListList(map, wnode.getName()));
			}
			if (isElementListList(wnode.getName())) {
				return new ElementListListResult(MapUtil.getElementListList(map, wnode.getName()));
			}
			if (isListListList(wnode.getName())) {
				return new ListListListResult(MapUtil.getListListList(map, wnode.getName()));
			}
			return new ElementListResult(MapUtil.getList(map, wnode.getName()));
		} else if (wnode instanceof MapNode) {
			return new MapResult(MapUtil.getMap(map, wnode.getName()));
		}
		return null;
	}

	@Override
	public Map<String, Object> getValue() {
		return map;
	}
	
	protected boolean isMapList(String key) {
		return MapUtil.getMapList(map, key) != null;
	}
	
	protected boolean isMapListList(String key) {
		return MapUtil.getMapListList(map, key) != null;
	}
	
	protected boolean isElementListList(String key) {
		return MapUtil.getElementListList(map, key) != null;
	}
	
	protected boolean isListListList(String key) {
		return MapUtil.getListListList(map, key) != null;
	}
}
