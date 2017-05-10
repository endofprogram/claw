package old.org.eop.claw.node.index;

import java.util.List;
import java.util.Map;

import old.org.eop.claw.node.IndexNode;
/**
 * lixinjie 2016-12-26
 */
public class DisperIndex extends IndexNode {

	public DisperIndex(String name, String type, List<Integer> indexList, Map<String, String> setting) {
		super(name, type, setting);
		this.indexList.addAll(indexList);
	}
	
	public List<Integer> getIndexes() {
		return indexList;
	}
}
