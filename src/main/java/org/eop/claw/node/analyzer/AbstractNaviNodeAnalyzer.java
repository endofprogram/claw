package org.eop.claw.node.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.StringUtil;
import org.eop.claw.node.NaviNode;
import org.eop.claw.node.NaviNodeAnalyzer;
import org.eop.claw.node.NodeSetting;
import org.eop.claw.node.ResultType;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public abstract class AbstractNaviNodeAnalyzer implements NaviNodeAnalyzer {

	protected String indexFlag;
	protected String depthFlag;
	
	protected AbstractNaviNodeAnalyzer(String indexFlag, String depthFlag) {
		this.indexFlag = indexFlag;
		this.depthFlag = depthFlag;
	}
	
	@Override
	public String getIndexFlag() {
		return indexFlag;
	}

	@Override
	public void setIndexFlag(String indexFlag) {
		this.indexFlag = indexFlag;
	}

	@Override
	public String getDepthFlag() {
		return depthFlag;
	}

	@Override
	public void setDepthFlag(String depthFlag) {
		this.depthFlag = depthFlag;
	}

	@Override
	public NaviNode analyze(String segment) {
		String name = analyzeName(segment);
		int depth = analyzeDepth(segment);
		ResultType resultType = analyzeResultType(segment);
		NodeSetting nodeSetting = analyzeNodeSetting(segment);
		return buildNaviNode(name, depth, resultType, nodeSetting, segment);
	}

	protected String analyzeName(String segment) {
		int endIndex = getNameEndIndex(segment);
		String name = segment.substring(0, endIndex);
		if (name.startsWith(indexFlag)) {
			return name.substring(indexFlag.length());
		}
		return name;
	}
	
	protected int analyzeDepth(String segment) {
		int index = segment.indexOf(depthFlag);
		if (index > -1) {
			Integer.parseInt(segment.substring(index + 1, index + 2));
		}
		return 0;
	}
	
	protected ResultType analyzeResultType(String segment) {
		if (segment.endsWith("}")) {
			return ResultType.Object;
		}
		if (segment.endsWith("]")) {
			return ResultType.List;
		}
		if (segment.endsWith(">")) {
			return ResultType.Element;
		}
		return null;
	}
	
	protected NodeSetting analyzeNodeSetting(String segment) {
		NodeSetting nodeSetting = new NodeSetting();
		int beginIndex = getSettingBeginIndex(segment);
		String settingStr = segment.substring(beginIndex, segment.length() - 1);
		if (EmptyUtil.notEmpty(settingStr)) {
			List<String> strList = new ArrayList<>();
			StringUtil.splitStr(settingStr, ",", strList);
			List<String> kvList = new ArrayList<>(2);
			for (String str : strList) {
				kvList.clear();
				StringUtil.splitStr(str, "=", kvList);
				nodeSetting.addSetting(kvList.get(0), kvList.get(1));
			}
		}
		return nodeSetting;
	}
	
	protected abstract NaviNode buildNaviNode(String name, int depth, ResultType resultType, NodeSetting nodeSetting, String segment);
	
	protected int getNameEndIndex(String segment) {
		String[] flags = {depthFlag, "{", "[", "<"};
		for (String flag : flags) {
			int endIndex = segment.indexOf(flag);
			if (endIndex > -1) {
				return endIndex;
			}
		}
		return 0;
	}
	
	protected int getSettingBeginIndex(String segment) {
		String[] flags = {"{", "[", "<"};
		for (String flag : flags) {
			int endIndex = segment.indexOf(flag);
			if (endIndex > -1) {
				return endIndex + 1;
			}
		}
		return 0;
	}
}
