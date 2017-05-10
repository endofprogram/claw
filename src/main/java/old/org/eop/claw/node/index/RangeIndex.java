package old.org.eop.claw.node.index;

import java.util.Map;

import old.org.eop.claw.node.IndexNode;
/**
 * lixinjie 2016-12-26
 */
public class RangeIndex extends IndexNode {

	public RangeIndex(String name, String type, Integer beginIndex, Integer endIndex, Map<String, String> setting) {
		super(name, type, setting);
		this.indexList.add(beginIndex);
		this.indexList.add(endIndex);
	}
	
	public Integer getBeginIndex() {
		return indexList.get(0);
	}
	
	public Integer getEndIndex() {
		return indexList.get(1);
	}
}
