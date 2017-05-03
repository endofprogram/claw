package org.eop.claw.node.result.json.netsfjson;

import java.util.List;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.ListUtil;
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
import org.eop.claw.util.NetsfJsonUtil;

import net.sf.json.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public class JsonObjectListResult extends RNode {

	protected List<JSONObject> jsonObjectList;
	
	public JsonObjectListResult(List<JSONObject> jsonObjectList) {
		super("[{}]");
		this.jsonObjectList = jsonObjectList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof IndexNode) {
			if (wnode instanceof SingleIndex) {
				return new JsonObjectResult(ListUtil.getItem(jsonObjectList, ((SingleIndex)wnode).getIndex()));
			} else if (wnode instanceof RangeIndex) {
				return new JsonObjectListResult(ListUtil.getList(jsonObjectList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
			} else if (wnode instanceof DisperIndex) {
				return new JsonObjectListResult(ListUtil.getList(jsonObjectList, ((DisperIndex)wnode).getIndexes()));
			}
			return null;
		} else if (wnode instanceof ElementNode) {
			return new ElementListResult(NetsfJsonUtil.getAllJsonValue(jsonObjectList, wnode.getName()));
		} else if (wnode instanceof MapNode) {
			return new JsonObjectListResult(NetsfJsonUtil.getAllJsonObject(jsonObjectList, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isJsonObjectListList(wnode.getName())) {
				return new JsonObjectListListResult(NetsfJsonUtil.getAllJsonObjectList(jsonObjectList, wnode.getName()));
			}
			if (isListListList(wnode.getName())) {
				return new ListListListResult(NetsfJsonUtil.getAllListList(jsonObjectList, wnode.getName()));
			}
			return new ElementListListResult(NetsfJsonUtil.getAllJsonValueList(jsonObjectList, wnode.getName()));
		}
		return null;
	}

	@Override
	public List<JSONObject> getValue() {
		return jsonObjectList;
	}
	
	protected boolean isJsonObjectListList(String key) {
		if (EmptyUtil.isEmpty(jsonObjectList)) {
			return false;
		}
		return NetsfJsonUtil.getJsonObjectList(ListUtil.getFirst(jsonObjectList), key) != null;
	}
	
	protected boolean isListListList(String key) {
		if (EmptyUtil.isEmpty(jsonObjectList)) {
			return false;
		}
		return NetsfJsonUtil.getListList(ListUtil.getFirst(jsonObjectList), key) != null;
	}
}
