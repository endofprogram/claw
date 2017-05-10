package old.org.eop.claw.node.element;

import java.sql.Timestamp;
import java.util.Map;

import old.org.eop.claw.node.ElementNode;
/**
 * lixinjie 2016-12-26
 */
public class TimestampElement extends ElementNode {

	public TimestampElement(String name, String type, Map<String, String> setting) {
		super(name, type, setting, Timestamp.class);
	}
}
