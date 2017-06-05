package org.eop.claw.node.result.map;

import java.util.List;
import java.util.Map;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.ElementResult;
import org.eop.claw.node.result.exception.ResultNodeException;

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
		return new MapResult((Map<String, Object>)getValue(nameNode));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractListResult getListResult(NameNode nameNode) {
		return new ListResult((List<Object>)getValue(nameNode));
	}

	@Override
	protected ElementResult getElementResult(NameNode nameNode) {
		return new ElementResult(getValue(nameNode));
	}

	@SuppressWarnings("unchecked")
	protected Object getValue(NameNode nameNode) {
		if (((Map<String, Object>)object).containsKey(nameNode.getName())) {
			return ((Map<String, Object>)object).get(nameNode.getName());
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}
}
