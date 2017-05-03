package org.eop.claw.node.result.json.fastjson;

import org.eop.claw.node.ElementNode;
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.navi.ListNode;
import org.eop.claw.node.navi.MapNode;
import org.eop.claw.node.result.ElementListListResult;
import org.eop.claw.node.result.ElementListResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.util.FastJsonUtil;

import com.alibaba.fastjson.JSONObject;
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
			return new ElementResult(FastJsonUtil.getJsonValue(jsonObject, wnode.getName()));
		} else if (wnode instanceof ListNode) {
			if (isJsonObjectList(wnode.getName())) {
				return new JsonObjectListResult(FastJsonUtil.getJsonObjectList(jsonObject, wnode.getName()));
			}
			if (isJsonObjectListList(wnode.getName())) {
				return new JsonObjectListListResult(FastJsonUtil.getJsonObjectListList(jsonObject, wnode.getName()));
			}
			if (isElementListList(wnode.getName())) {
				return new ElementListListResult(FastJsonUtil.getElementListList(jsonObject, wnode.getName()));
			}
			if (isListListList(wnode.getName())) {
				return new ListListListResult(FastJsonUtil.getListListList(jsonObject, wnode.getName()));
			}
			return new ElementListResult(FastJsonUtil.getJsonValueList(jsonObject, wnode.getName()));
		} else if (wnode instanceof MapNode) {
			return new JsonObjectResult(FastJsonUtil.getJsonObject(jsonObject, wnode.getName()));
		}
		return null;
	}

	@Override
	public JSONObject getValue() {
		return jsonObject;
	}
	
	protected boolean isJsonObjectList(String key) {
		return FastJsonUtil.getJsonObjectList(jsonObject, key) != null;
	}
	
	protected boolean isJsonObjectListList(String key) {
		return FastJsonUtil.getJsonObjectListList(jsonObject, key) != null;
	}
	
	protected boolean isElementListList(String key) {
		return FastJsonUtil.getElementListList(jsonObject, key) != null;
	}
	
	protected boolean isListListList(String key) {
		return FastJsonUtil.getListListList(jsonObject, key) != null;
	}
}
