package org.eop.claw.internal.bean;

import org.eop.claw.AbstractClaw;
import org.eop.claw.ClawSetting;
import org.eop.claw.IClaw;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.result.bean.BeanResult;

/**
 * @author lixinjie
 * @since 2017-06-09
 */
public class BeanClaw extends AbstractClaw {

	public BeanClaw(Object bean) {
		this(new BeanResult(bean));
	}
	
	public BeanClaw(Object bean, ClawSetting clawSetting) {
		this(new BeanResult(bean), clawSetting);
	}
	
	protected BeanClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}
	
	protected BeanClaw(ResultNode rootResultNode, ClawSetting clawSetting) {
		super(rootResultNode, clawSetting);
	}

	@Override
	protected IClaw getClaw() {
		return new BeanClaw(currentResultNode, clawSetting);
	}

}
