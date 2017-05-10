package old.org.eop.claw.node.result.xml.dom4j;

import java.util.List;

import org.dom4j.Element;
import org.eop.chassis.util.ListUtil;

import old.org.eop.claw.node.RNode;
import old.org.eop.claw.node.WNode;
import old.org.eop.claw.node.index.DisperIndex;
import old.org.eop.claw.node.index.RangeIndex;
import old.org.eop.claw.node.index.SingleIndex;
/**
 * lixinjie 2016-12-26
 */
public class Dom4jElementListListResult extends RNode {

	protected List<List<Element>> elementListList;
	
	public Dom4jElementListListResult(List<List<Element>> elementListList) {
		super("[[{}]]");
		this.elementListList = elementListList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof SingleIndex) {
			return new Dom4jElementListResult(ListUtil.getAllAt(elementListList, ((SingleIndex)wnode).getIndex()));
		} else if (wnode instanceof RangeIndex) {
			return new Dom4jElementListListResult(ListUtil.getAllRange(elementListList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
		} else if (wnode instanceof DisperIndex) {
			return new Dom4jElementListListResult(ListUtil.getAllDisper(elementListList, ((DisperIndex)wnode).getIndexes()));
		}
		return null;
	}

	@Override
	public List<List<Element>> getValue() {
		return elementListList;
	}
}
