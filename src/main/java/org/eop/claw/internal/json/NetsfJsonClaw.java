package org.eop.claw.internal.json;

import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;
import org.eop.claw.node.RNode;
import org.eop.claw.node.result.json.netsfjson.JsonObjectResult;

import net.sf.json.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public class NetsfJsonClaw extends AbstractClaw {

	public NetsfJsonClaw(JSONObject jsonObject) {
		this(new JsonObjectResult(jsonObject));
	}
	
	protected NetsfJsonClaw(RNode rootRNode) {
		super(rootRNode);
	}
	
	@Override
	public IClaw getClaw(String path) {
		crawlRNode(path);
		return new NetsfJsonClaw(currentRNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}

}
