package old.org.eop.claw.node.element;

import java.math.BigDecimal;
import java.util.Map;

import old.org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class BigDecimalElement extends ElementNode {

	public BigDecimalElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, BigDecimal.class);
	}
}
