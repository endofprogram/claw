package org.eop.claw.internal.json;

import org.eop.claw.AbstractClaw;
import org.eop.claw.ClawSetting;
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
	
	public FastJsonClaw(JSONObject json, ClawSetting clawSetting) {
		this(new FastJsonResult(json), clawSetting);
	}
	
	protected FastJsonClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}
	
	protected FastJsonClaw(ResultNode rootResultNode, ClawSetting clawSetting) {
		super(rootResultNode, clawSetting);
	}

	@Override
	protected IClaw getClaw() {
		return new FastJsonClaw(currentResultNode, clawSetting);
	}

}
