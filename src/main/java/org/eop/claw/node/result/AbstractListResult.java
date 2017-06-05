package org.eop.claw.node.result;

import java.util.ArrayList;
import java.util.List;

import org.eop.chassis.util.IndexUtil;
import org.eop.claw.node.NaviNode;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.IndexNode;
import org.eop.claw.node.navi.NameNode;
import org.eop.claw.node.navi.index.DisperIndex;
import org.eop.claw.node.navi.index.RangeIndex;
import org.eop.claw.node.navi.index.SingleIndex;
import org.eop.claw.node.result.exception.ResultNodeException;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class AbstractListResult extends AbstractResultNode {

	protected List<Object> objects;
	
	protected AbstractListResult(List<Object> objects) {
		super("[]");
		this.objects = objects;
	}

	@Override
	public ResultNode getResult(NaviNode naviNode) {
		if (naviNode instanceof IndexNode) {
			if (naviNode.getResultType() == ResultType.Object) {
				return getObjectResult((SingleIndex)naviNode);
			}
			if (naviNode.getResultType() == ResultType.List) {
				return getListResult((IndexNode)naviNode);
			}
			if (naviNode.getResultType() == ResultType.Element) {
				return getElementResult((SingleIndex)naviNode);
			}
		}
		if (naviNode instanceof NameNode) {
			return getListResult((NameNode)naviNode);
		}
		throw new ResultNodeException("only allowed to navi by name or index at this point, segment '" + naviNode.getSegment() + "' is invalid");
	}

	@Override
	public List<Object> getValue() {
		return objects;
	}
	
	protected AbstractObjectResult getObjectResult(SingleIndex singleIndex) {
		Object object = objects.get(IndexUtil.transform(singleIndex.getIndex(), objects.size()));
		return getObjectResult(object);
	}
	
	@SuppressWarnings("unchecked")
	protected AbstractListResult getListResult(IndexNode indexNode) {
		List<Object> objectList = (List<Object>)getListResultRecursively(objects, indexNode, indexNode.getDepth());
		return getListResult(objectList);
	}
	
	@SuppressWarnings("unchecked")
	protected Object getListResultRecursively(List<Object> objects, IndexNode indexNode, int depth) {
		List<Object> objectList = new ArrayList<>();
		if (depth > 0) {
			for (Object object : objects) {
				objectList.add(getListResultRecursively((List<Object>)object, indexNode, depth - 1));
			}
		} else {
			if (indexNode instanceof SingleIndex) {
				return getValueByIndex(objects, (SingleIndex)indexNode);
			} else if (indexNode instanceof RangeIndex) {
				return getValueByIndexes(objects, (RangeIndex)indexNode);
			} else {
				return getValueByIndexes(objects, (DisperIndex)indexNode);
			}
		}
		return objectList;
	}
	
	protected Object getValueByIndex(List<Object> objects, SingleIndex singleIndex) {
		return objects.get(IndexUtil.transform(singleIndex.getIndex(), objects.size()));
	}
	
	protected List<Object> getValueByIndexes(List<Object> objects, RangeIndex rangeIndex) {
		Integer beginIndex = IndexUtil.transform(rangeIndex.getBeginIndex(), objects.size());
		Integer endIndex = IndexUtil.transform(rangeIndex.getEndIndex(), objects.size(), 1);
		List<Object> objectList = new ArrayList<>(endIndex - beginIndex);
		for (int index = beginIndex; index < endIndex; index++) {
			objectList.add(objects.get(index));
		}
		return objectList;
	}
	
	protected List<Object> getValueByIndexes(List<Object> objects, DisperIndex disperIndex) {
		List<Integer> indexes = disperIndex.getIndexes();
		List<Object> objectList = new ArrayList<>(indexes.size());
		for (Integer index : indexes) {
			objectList.add(objects.get(IndexUtil.transform(index, objects.size())));
		}
		return objectList;
	}
	
	protected ElementResult getElementResult(SingleIndex singleIndex) {
		return new ElementResult(objects.get(IndexUtil.transform(singleIndex.getIndex(), objects.size())));
	}

	protected AbstractListResult getListResult(NameNode nameNode) {
		List<Object> objectList = getListResultRecursively(objects, nameNode, nameNode.getDepth());
		return getListResult(objectList);
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object> getListResultRecursively(List<Object> objects, NameNode nameNode, int depth) {
		List<Object> objectList = new ArrayList<>();
		for (Object object : objects) {
			if (depth > 0) {
				objectList.add(getListResultRecursively((List<Object>)object, nameNode, depth - 1));
			} else {
				objectList.add(getValueByName(object, nameNode));
			}
		}
		return objectList;
	}
	
	protected abstract AbstractObjectResult getObjectResult(Object object);
	
	protected abstract AbstractListResult getListResult(List<Object> objects);
	
	protected abstract Object getValueByName(Object object, NameNode nameNode);
}
