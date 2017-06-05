package org.eop.claw.node.result.json.fast;

import java.util.List;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.node.result.exception.ResultNodeException;

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
		return new FastJsonResult((JSONObject)getValue(nameNode));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		return new ListResult((List<Object>)getValue(nameNode));
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		return new ElementResult(getValue(nameNode));
	}

	protected Object getValue(NameNode nameNode) {
		if (((JSONObject)object).containsKey(nameNode.getName())) {
			return ((JSONObject)object).get(nameNode.getName());
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}
}
