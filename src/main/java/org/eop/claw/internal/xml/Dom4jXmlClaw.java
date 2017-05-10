package org.eop.claw.internal.xml;

import org.dom4j.Element;
import org.eop.claw.AbstractClaw;
import org.eop.claw.IClaw;

import old.org.eop.claw.node.RNode;
import old.org.eop.claw.node.result.xml.dom4j.Dom4jElementResult;
/**
 * lixinjie 2016-12-26
 */
public class Dom4jXmlClaw extends AbstractClaw {

	public Dom4jXmlClaw(Element element) {
		this(new Dom4jElementResult(element));
	}
	
	protected Dom4jXmlClaw(RNode rootRNode) {
		super(rootRNode);
	}

	@Override
	public IClaw getClaw(String path) {
		crawlRNode(path);
		return new Dom4jXmlClaw(currentRNode);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return null;
	}
}
