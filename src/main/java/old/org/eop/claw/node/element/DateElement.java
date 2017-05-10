package old.org.eop.claw.node.element;

import java.util.Date;
import java.util.Map;

import old.org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class DateElement extends ElementNode {

	public DateElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, Date.class);
	}
}
