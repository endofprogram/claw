package org.eop.claw.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.StringUtil;
/**
 * lixinjie 2016-12-26
 */
public abstract class WNodeAnalyzer {

	protected String left;
	protected String right;
	
	protected String wnodeName;
	protected String wnodeType;
	protected Map<String, String> wnodeSetting;
	
	protected WNodeAnalyzer(String left, String right) {
		this.left = left;
		this.right = right;
	}
	
	public WNode analyze(String segment) {
		wnodeName = analyzeWnodeName(segment);
		wnodeType = analyzeWnodeType(segment);
		wnodeSetting = analyzeWnodeSetting(segment);
		doMoreAnalyze(segment);
		return buildWnode(segment);
	}
	
	protected void doMoreAnalyze(String segment) {
		
	}
	
	protected abstract WNode buildWnode(String segment);
	
	protected String analyzeWnodeName(String segment) {
		return segment.substring(0, segment.indexOf(left));
	}
	
	protected String analyzeWnodeType(String segment) {
		return left + right;
	}
	
	protected Map<String, String> analyzeWnodeSetting(String segment) {
		Map<String, String> setting = new HashMap<>();
		String settingStr = segment.substring(segment.indexOf(left) + 1, segment.length() - 1);
		if (EmptyUtil.notEmpty(settingStr)) {
			List<String> strList = new ArrayList<>();
			StringUtil.splitStr(settingStr, ",", strList);
			List<String> kvList = new ArrayList<>(2);
			for (String str : strList) {
				kvList.clear();
				StringUtil.splitStr(str, "=", kvList);
				setting.put(kvList.get(0), kvList.get(1));
			}
		}
		return setting;
	}
}
