package org.eop.claw.node.result.json.fast;

import java.util.List;

import org.eop.claw.node.navi.index.SingleIndex;
import org.eop.claw.node.result.AbstractListResult;
import org.eop.claw.node.result.AbstractObjectResult;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lixinjie
 * @since 2017-05-11
 */
public class ListResult extends AbstractListResult {

	public ListResult(List<Object> objects) {
		super(objects);
	}

	@Override
	protected AbstractObjectResult getObjectResult(SingleIndex singleIndex) {
		return new FastJsonResult((JSONObject)objects.get(singleIndex.getIndex()));
	}

	@Override
	protected AbstractListResult getListResult(List<Object> objects) {
		return new ListResult(objects);
	}

	@Override
	protected Object getValueByName(Object object, String name) {
		return ((JSONObject)object).get(name);
	}

}
