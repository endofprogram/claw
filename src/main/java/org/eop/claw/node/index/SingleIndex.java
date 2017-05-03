package org.eop.claw.node.index;

import java.util.Map;

import org.eop.claw.node.IndexNode;
/**
 * lixinjie 2016-12-26
 */
public class SingleIndex extends IndexNode {

	public SingleIndex(String name, String type, Integer index, Map<String, String> setting) {
		super(name, type, setting);
		this.indexList.add(index);
	}
	
	public Integer getIndex() {
		return indexList.get(0);
	}
}
