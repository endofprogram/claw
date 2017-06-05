package org.eop.claw.internal.xml;

import org.dom4j.Element;
import org.eop.claw.AbstractClaw;
import org.eop.claw.ClawSetting;
import org.eop.claw.IClaw;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.result.xml.dom4j.Dom4jElementResult;
/**
 * lixinjie 2016-12-26
 */
public class Dom4jXmlClaw extends AbstractClaw {

	public Dom4jXmlClaw(Element element) {
		this(new Dom4jElementResult(element));
	}
	
	public Dom4jXmlClaw(Element element, ClawSetting clawSetting) {
		this(new Dom4jElementResult(element), clawSetting);
	}
	
	protected Dom4jXmlClaw(ResultNode rootResultNode) {
		super(rootResultNode);
	}
	
	protected Dom4jXmlClaw(ResultNode rootResultNode, ClawSetting clawSetting) {
		super(rootResultNode, clawSetting);
	}

	@Override
	protected IClaw getClaw() {
		return new Dom4jXmlClaw(currentResultNode, clawSetting);
	}

}
