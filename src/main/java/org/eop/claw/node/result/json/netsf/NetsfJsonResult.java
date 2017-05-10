package org.eop.claw.node.result.json.netsf;

import java.util.List;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;

import net.sf.json.JSONObject;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class NetsfJsonResult extends AbstractObjectResult {

	protected NetsfJsonResult(JSONObject json) {
		super(json);
	}

	@Override
	protected AbstractObjectResult getObjectResult(NameNode nameNode) {
		return new NetsfJsonResult((JSONObject)getValue(nameNode.getName()));
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
