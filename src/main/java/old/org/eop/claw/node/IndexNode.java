package old.org.eop.claw.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eop.chassis.util.TypeUtil;
/**
 * lixinjie 2016-12-26
 */
public class IndexNode extends WNode {

	protected List<Integer> indexList = new ArrayList<>();
	
	public IndexNode(String name, String type, Map<String, String> setting) {
		super(name, type, setting);
		this.elementType = TypeUtil.getType(setting.get("type"));
	}
	
}
