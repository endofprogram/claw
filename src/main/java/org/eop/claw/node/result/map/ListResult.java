package org.eop.claw.node.result.map;

import java.util.List;
import java.util.Map;

import org.eop.claw.node.navi.index.SingleIndex;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;

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
	protected AbstractObjectResult getObjectResult(SingleIndex singleIndex) {
		return new MapResult((Map<String, Object>)objects.get(singleIndex.getIndex()));
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object getValueByName(Object object, String name) {
		return ((Map<String, Object>)object).get(name);
	}
	
}
