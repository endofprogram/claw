package org.eop.claw.internal.json;

import org.eop.claw.AbstractClaw;
import org.eop.claw.ClawSetting;
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
	
	public NetsfJsonClaw(JSONObject json, ClawSetting clawSetting) {
		this(new NetsfJsonResult(json), clawSetting);
	}
	
	protected NetsfJsonClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}
	
	protected NetsfJsonClaw(ResultNode rootResultNode, ClawSetting clawSetting) {
		super(rootResultNode, clawSetting);
	}
	
	@Override
	protected IClaw getClaw() {
		return new NetsfJsonClaw(currentResultNode, clawSetting);
	}

}
