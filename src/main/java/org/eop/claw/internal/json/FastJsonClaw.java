package org.eop.claw.internal.json;

import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.result.json.fast.FastJsonResult;

import com.alibaba.fastjson.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public class FastJsonClaw extends AbstractClaw {

	public FastJsonClaw(JSONObject json) {
		this(new FastJsonResult(json));
	}
	
	protected FastJsonClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}

	@Override
	public IClaw getClaw(String path) {
		crawlResultNode(path);
		return new FastJsonClaw(currentResultNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}
}
