package org.eop.claw.internal.xml;

import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;
import org.eop.claw.node.RNode;
import org.eop.claw.node.result.xml.w3c.W3cElementResult;
import org.w3c.dom.Element;
/**
 * lixinjie 2016-12-26
 */
public class W3cXmlClaw extends AbstractClaw {

	public W3cXmlClaw(Element element) {
		this(new W3cElementResult(element));
	}
	
	protected W3cXmlClaw(RNode rootRNode) {
		super(rootRNode);
	}
	
	@Override
	public IClaw getClaw(String path) {
		crawlRNode(path);
		return new W3cXmlClaw(currentRNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}

}
