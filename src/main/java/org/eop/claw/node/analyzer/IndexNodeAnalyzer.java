package org.eop.claw.node.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.eop.chassis.util.StringUtil;
import org.eop.claw.node.NaviNode;
import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;
import org.eop.claw.node.navi.index.DisperIndex;
import org.eop.claw.node.navi.index.RangeIndex;
import org.eop.claw.node.navi.index.SingleIndex;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class IndexNodeAnalyzer extends AbstractNaviNodeAnalyzer {

	public IndexNodeAnalyzer(String indexFlag, String depthFlag) {
		super(indexFlag, depthFlag);
	}

	@Override
	protected NaviNode buildNaviNode(String name, int depth, ResultType resultType, NodeSetting nodeSetting,
			String segment) {
		if (name.contains("*")) {
			List<Integer> indexes = analyzeIndexes(name, "*");
			return new RangeIndex(segment, name, depth, resultType, nodeSetting, indexes.get(0), indexes.get(1));
		} else if (name.contains("+")) {
			List<Integer> indexes = analyzeIndexes(name, "+");
			return new DisperIndex(segment, name, depth, resultType, nodeSetting, indexes);
		} else {
			List<Integer> indexes = analyzeIndexes(name, "");
			return new SingleIndex(segment, name, depth, resultType, nodeSetting, indexes.get(0));
		}
	}

	protected List<Integer> analyzeIndexes(String name, String delimiter) {
		List<String> idxes = new ArrayList<>();
		StringUtil.splitStr(name, delimiter, idxes);
		List<Integer> indexes = new ArrayList<>(idxes.size());
		for (String idx : idxes) {
			indexes.add(Integer.valueOf(idx));
		}
		return indexes;
	}
}
