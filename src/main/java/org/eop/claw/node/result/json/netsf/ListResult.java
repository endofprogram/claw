package org.eop.claw.node.result.json.netsf;

import java.util.List;

import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;
import org.eop.claw.node.result.exception.ResultNodeException;

import net.sf.json.JSONObject;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class ListResult extends AbstractListResult {

	protected ListResult(List<Object> objects) {
		super(objects);
	}

	@Override
	protected AbstractObjectResult getObjectResult(Object object) {
		return new NetsfJsonResult((JSONObject)object);
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@Override
	protected Object getValueByName(Object object, NameNode nameNode) {
		if (((JSONObject)object).containsKey(nameNode.getName())) {
			return ((JSONObject)object).get(nameNode.getName());
		}
		throw new ResultNodeException("not contains a subnode with name '" + nameNode.getName() + "' at this point, see segment '" + nameNode.getSegment() + "' or the current result node");
	}

}
