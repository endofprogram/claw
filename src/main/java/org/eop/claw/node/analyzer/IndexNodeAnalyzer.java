package org.eop.claw.node.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.eop.chassis.util.StringUtil;
import org.eop.claw.node.WNode;
import org.eop.claw.node.WNodeAnalyzer;
import org.eop.claw.node.index.DisperIndex;
import org.eop.claw.node.index.RangeIndex;
import org.eop.claw.node.index.SingleIndex;
/**
 * lixinjie 2016-12-26
 */
public class IndexNodeAnalyzer extends WNodeAnalyzer {

	protected List<Integer> indexList = new ArrayList<>();
	protected int indexType;
	
	public IndexNodeAnalyzer() {
		super("(", ")");
	}
	
	@Override
	protected WNode buildWnode(String segment) {
		if (indexType == 2) {
			return new RangeIndex(wnodeName, wnodeType, indexList.get(0), indexList.get(1), wnodeSetting);
		} else if (indexType == 3) {
			return new DisperIndex(wnodeName, wnodeType, indexList, wnodeSetting);
		} else if(indexType == 1) {
			return new SingleIndex(wnodeName, wnodeType, indexList.get(0), wnodeSetting);
		}
		return null;
	}

	@Override
	protected void doMoreAnalyze(String segment) {
		List<String> idxList = new ArrayList<>();
		if (wnodeName.indexOf("*") > 0) {
			StringUtil.splitStr(wnodeName, "*", idxList);
			indexType = 2;
		} else if (wnodeName.indexOf("+") > 0) {
			StringUtil.splitStr(wnodeName, "+", idxList);
			indexType = 3;
		} else {
			StringUtil.splitStr(wnodeName, "", idxList);
			indexType = 1;
		}
		indexList.clear();
		for (String idx : idxList) {
			indexList.add(Integer.valueOf(idx));
		}
	}

}
