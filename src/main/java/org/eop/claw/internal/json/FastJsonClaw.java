package org.eop.claw.internal.json;

import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;
import org.eop.claw.node.RNode;
import org.eop.claw.node.result.json.fastjson.JsonObjectResult;

import com.alibaba.fastjson.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public class FastJsonClaw extends AbstractClaw {

	public FastJsonClaw(JSONObject jsonObject) {
		this(new JsonObjectResult(jsonObject));
	}
	
	protected FastJsonClaw(RNode rootRNode) {
		super(rootRNode);
	}

	@Override
	public IClaw getClaw(String path) {
		crawlRNode(path);
		return new FastJsonClaw(currentRNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}
}
