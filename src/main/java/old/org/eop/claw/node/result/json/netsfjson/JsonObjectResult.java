package old.org.eop.claw.node.result.json.netsfjson;

import net.sf.json.JSONObject;
import old.org.eop.claw.node.ElementNode;
import old.org.eop.claw.node.RNode;
import old.org.eop.claw.node.WNode;
import old.org.eop.claw.node.navi.ListNode;
import old.org.eop.claw.node.navi.MapNode;
import old.org.eop.claw.node.result.ElementListListResult;
import old.org.eop.claw.node.result.ElementListResult;
import old.org.eop.claw.node.result.ElementResult;
import old.org.eop.claw.util.NetsfJsonUtil;
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
