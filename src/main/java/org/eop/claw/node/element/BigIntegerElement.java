package org.eop.claw.node.element;

import java.math.BigInteger;
import java.util.Map;

import org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class BigIntegerElement extends ElementNode {

	public BigIntegerElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, BigInteger.class);
	}
}
