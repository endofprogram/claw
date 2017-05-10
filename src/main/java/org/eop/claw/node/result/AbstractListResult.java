package org.eop.claw.node.result;

import java.util.ArrayList;
import java.util.List;

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
		throw new ResultNodeException("", naviNode.getSegment());
	}

	@Override
	public List<Object> getValue() {
		return objects;
	}
	
	protected abstract AbstractObjectResult getObjectResult(SingleIndex singleIndex);
	
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
				if (((RangeIndex)indexNode).getBeginIndex() == 0 && ((RangeIndex)indexNode).getEndIndex() == -1) {
					objectList.addAll(getValueByIndexes(objects, (RangeIndex)indexNode));
				} else {
					objectList.add(getValueByIndexes(objects, (RangeIndex)indexNode));
				}
			} else {
				objectList.add(getValueByIndexes(objects, (DisperIndex)indexNode));
			}
		}
		return objectList;
	}
	
	protected Object getValueByIndex(List<Object> objects, SingleIndex singleIndex) {
		return objects.get(singleIndex.getIndex());
	}
	
	protected List<Object> getValueByIndexes(List<Object> objects, RangeIndex rangeIndex) {
		Integer beginIndex = rangeIndex.getBeginIndex();
		Integer endIndex = rangeIndex.getEndIndex();
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
			objectList.add(objects.get(index));
		}
		return objectList;
	}
	
	protected ElementResult getElementResult(SingleIndex singleIndex) {
		return new ElementResult(objects.get(singleIndex.getIndex()));
	}

	protected AbstractListResult getListResult(NameNode nameNode) {
		List<Object> objectList = getListResultRecursively(objects, nameNode.getName(), nameNode.getDepth());
		return getListResult(objectList);
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object> getListResultRecursively(List<Object> objects, String name, int depth) {
		List<Object> objectList = new ArrayList<>();
		for (Object object : objects) {
			if (depth > 0) {
				objectList.add(getListResultRecursively((List<Object>)object, name, depth - 1));
			} else {
				objectList.add(getValueByName(object, name));
			}
		}
		return objectList;
	}
	
	protected abstract AbstractListResult getListResult(List<Object> objects);
	
	protected abstract Object getValueByName(Object object, String name);
}
