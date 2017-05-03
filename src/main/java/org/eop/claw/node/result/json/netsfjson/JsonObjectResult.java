package org.eop.claw.node.result.json.netsfjson;

import org.eop.claw.node.ElementNode;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.navi.ListNode;
import org.eop.claw.node.navi.MapNode;
import org.eop.claw.node.result.ElementListListResult;
import org.eop.claw.node.result.ElementListResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.util.NetsfJsonUtil;

import net.sf.json.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public class JsonObjectResult extends RNode {

	protected JSONObject jsonObject;
	
	public JsonObjectResult(JSONObject jsonObject) {
		super("{}");
		this.jsonObject = jsonObject;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof ElementNode) {
			return new ElementResult(NetsfJsonUtil.getJsonValue(jsonObject, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isJsonObjectList(wnode.getName())) {
				return new JsonObjectListResult(NetsfJsonUtil.getJsonObjectList(jsonObject, wnode.getName()));
			}
			if (isJsonObjectListList(wnode.getName())) {
				return new JsonObjectListListResult(NetsfJsonUtil.getJsonObjectListList(jsonObject, wnode.getName()));
			}
			if (isElementListList(wnode.getName())) {
				return new ElementListListResult(NetsfJsonUtil.getElementListList(jsonObject, wnode.getName()));
			}
			if (isListListList(wnode.getName())) {
				return new ListListListResult(NetsfJsonUtil.getListListList(jsonObject, wnode.getName()));
			}
			return new ElementListResult(NetsfJsonUtil.getJsonValueList(jsonObject, wnode.getName()));
		} else if (wnode instanceof MapNode) {
			return new JsonObjectResult(NetsfJsonUtil.getJsonObject(jsonObject, wnode.getName()));
		}
		return null;
	}

	@Override
	public JSONObject getValue() {
		return jsonObject;
	}
	
	protected boolean isJsonObjectList(String key) {
		return NetsfJsonUtil.getJsonObjectList(jsonObject, key) != null;
	}
	
	protected boolean isJsonObjectListList(String key) {
		return NetsfJsonUtil.getJsonObjectListList(jsonObject, key) != null;
	}
	
	protected boolean isElementListList(String key) {
		return NetsfJsonUtil.getElementListList(jsonObject, key) != null;
	}
	
	protected boolean isListListList(String key) {
		return NetsfJsonUtil.getListListList(jsonObject, key) != null;
	}
}
