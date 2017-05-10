package old.org.eop.claw.node.result.xml.w3c;

import java.util.List;

import org.eop.chassis.util.ListUtil;
import org.w3c.dom.Element;

import old.org.eop.claw.node.RNode;
import old.org.eop.claw.node.WNode;
import old.org.eop.claw.node.index.DisperIndex;
import old.org.eop.claw.node.index.RangeIndex;
import old.org.eop.claw.node.index.SingleIndex;
/**
 * lixinjie 2016-12-26
 */
public class W3cElementListListResult extends RNode {

	protected List<List<Element>> elementListList;
	
	public W3cElementListListResult(List<List<Element>> elementListList) {
		super("[[{}]]");
		this.elementListList = elementListList;
	}

	@Override
	public RNode getResult(WNode wnode) {
		if (wnode instanceof SingleIndex) {
			return new W3cElementListResult(ListUtil.getAllAt(elementListList, ((SingleIndex)wnode).getIndex()));
		} else if (wnode instanceof RangeIndex) {
			return new W3cElementListListResult(ListUtil.getAllRange(elementListList, ((RangeIndex)wnode).getBeginIndex(), ((RangeIndex)wnode).getEndIndex()));
		} else if (wnode instanceof DisperIndex) {
			return new W3cElementListListResult(ListUtil.getAllDisper(elementListList, ((DisperIndex)wnode).getIndexes()));
		}
		return null;
	}

	@Override
	public List<List<Element>> getValue() {
		return elementListList;
	}
}
