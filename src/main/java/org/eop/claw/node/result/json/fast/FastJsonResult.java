package org.eop.claw.node.result.json.fast;

import java.util.List;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class FastJsonResult extends AbstractObjectResult {

	public FastJsonResult(JSONObject json) {
		super(json);
	}

	@Override
	protected AbstractObjectResult getObjectResult(NameNode nameNode) {
		return new FastJsonResult((JSONObject)getValue(nameNode.getName()));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		return new ListResult((List<Object>)getValue(nameNode.getName()));
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		return new ElementResult(getValue(nameNode.getName()));
	}

	protected Object getValue(String key) {
		return ((JSONObject)object).get(key);
	}
}
