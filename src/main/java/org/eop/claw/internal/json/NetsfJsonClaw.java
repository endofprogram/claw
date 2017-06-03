package org.eop.claw.internal.json;

import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.result.json.netsf.NetsfJsonResult;

import net.sf.json.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public class NetsfJsonClaw extends AbstractClaw {

	public NetsfJsonClaw(JSONObject json) {
		this(new NetsfJsonResult(json));
	}
	
	protected NetsfJsonClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}
	
	@Override
	public IClaw getClaw(String path) {
		crawlResultNode(path);
		return new NetsfJsonClaw(currentResultNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}

}
