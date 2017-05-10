package org.eop.claw.node.result.map;

import java.util.List;
import java.util.Map;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class MapResult extends AbstractObjectResult {

	public MapResult(Map<String, Object> map) {
		super(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractObjectResult getObjectResult(NameNode nameNode) {
		return new MapResult((Map<String, Object>)getValue(nameNode.getName()));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		return new ListResult((List<Object>)getValue(nameNode.getName()));
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		return new ElementResult(getValue(nameNode.getName()));
	}

	@SuppressWarnings("unchecked")
	protected Object getValue(String key) {
		return ((Map<String, Object>)object).get(key);
	}
}
