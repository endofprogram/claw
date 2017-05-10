package old.org.eop.claw.node.element;

import java.util.Map;

import old.org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class ShortElement extends ElementNode {

	public ShortElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, Short.class);
	}
}
