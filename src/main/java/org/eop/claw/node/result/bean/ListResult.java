package org.eop.claw.node.result.bean;

import java.util.List;
import java.util.Map;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-06-09
 */
public class ListResult extends AbstractListResult {

	public ListResult(List<Object> objects) {
		super(objects);
	}

	@Override
	protected AbstractObjectResult getObjectResult(Object object) {
		return new BeanResult(object);
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object getValueByName(Object object, NameNode nameNode) {
		if (object instanceof Map<?, ?>) {
			if (((Map<String, Object>)object).containsKey(nameNode.getName())) {
				return ((Map<String, Object>)object).get(nameNode.getName());
			}
		} else {
			try {
				return object.getClass().getMethod("get" + nameNode.getName().substring(0, 1).toUpperCase() + nameNode.getName().substring(1)).invoke(object);
			} catch (Exception e) {
				throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node", e);
			}
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}
	
}
