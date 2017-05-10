package old.org.eop.claw.node.result.map;

import java.util.List;

import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;

import old.org.eop.claw.node.RNode;
import old.org.eop.claw.node.WNode;
import old.org.eop.claw.node.index.DisperIndex;
import old.org.eop.claw.node.index.RangeIndex;
import old.org.eop.claw.node.index.SingleIndex;
import old.org.eop.claw.node.result.ElementListListResult;

/**
 * @author lixinjie 201704-13
 */
public class ListListListResult extends RNode {

	protected List<List<List<Object>>> listListList;
	
	public ListListListResult(List<List<List<Object>>> listListList) {
		super("[[[]]]");
		this.listListList = listListList;
	}
	
	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof SingleIndex) {
			if (isMapListList()) {
				return new MapListListResult(ListUtil.getMapListList(listListList, ((SingleIndex)wnode).getIndex()));
			}
			if (isListListList()) {
				return new ListListListResult(ListUtil.getListListList(listListList, ((SingleIndex)wnode).getIndex()));
			}
			return new ElementListListResult(ListUtil.getElementListList(listListList, ((SingleIndex)wnode).getIndex()));
		} else if (wnode instanceof RangeIndex) {
			return new ListListListResult(ListUtil.getListListList(listListList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
		} else if (wnode instanceof DisperIndex) {
			return new ListListListResult(ListUtil.getListListList(listListList, ((DisperIndex)wnode).getIndexes()));
		}
		return null;
	}

	@Override
	public List<List<List<Object>>> getValue() {
		return listListList;
	}

	protected boolean isMapListList() {
		return TypeUtil.isMap(ListUtil.getFirst(ListUtil.getFirst(ListUtil.getFirst(listListList))));
	}
	
	protected boolean isListListList() {
		return TypeUtil.isList(ListUtil.getFirst(ListUtil.getFirst(ListUtil.getFirst(listListList))));
	}
}
