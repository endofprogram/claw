package org.eop.claw.node.result.map;

import java.util.List;
import java.util.Map;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class ListResult extends AbstractListResult {

	public ListResult(List<Object> objects) {
		super(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AbstractObjectResult getObjectResult(Object object) {
		return new MapResult((Map<String, Object>)object);
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object getValueByName(Object object, NameNode nameNode) {
		if (((Map<String, Object>)object).containsKey(nameNode.getName())) {
			return ((Map<String, Object>)object).get(nameNode.getName());
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}
	
}
