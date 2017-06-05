package org.eop.claw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eop.chassis.util.StringUtil;
import org.eop.claw.exception.ResultException;
import org.eop.claw.node.NaviNode;
import org.eop.claw.node.NaviNodeAnalyzer;
import org.eop.claw.node.ResultNode;
import org.eop.claw.node.analyzer.IndexNodeAnalyzer;
import org.eop.claw.node.analyzer.NameNodeAnalyzer;
/**
 * lixinjie 2016-12-26
 */
public abstract class AbstractClaw implements IClaw {

	protected List<NaviNode> naviNodeList = new ArrayList<>();
	protected List<String> segmentList = new ArrayList<>();
	protected Map<String, NaviNodeAnalyzer> naviNodeAnalyzers = new HashMap<>();
	protected ResultNode rootResultNode;
	protected ResultNode currentResultNode;
	protected ClawSetting clawSetting;
	
	protected AbstractClaw(ResultNode rootResultNode) {
		this(rootResultNode, new ClawSetting());
	}
	
	protected AbstractClaw(ResultNode rootResultNode, ClawSetting clawSetting) {
		this.rootResultNode = rootResultNode;
		this.clawSetting = clawSetting;
		registerNaviNodeAnalyzer();
	}

	@Override
	public IResult getResult(String path) {
		try {
			crawlResultNode(path);
		} catch (Exception e) {
			return new Result(false, null, new ResultException(e));
		}
		return new Result(true, getClaw(), null);
	}

	protected abstract IClaw getClaw();
	
	@Override
	public Object getUnderlyingData() {
		return rootResultNode.getValue();
	}
	
	protected void crawlResultNode(String path) {
		transformPathToNodeList(path);
		currentResultNode = rootResultNode;
		for (NaviNode naviNode : naviNodeList) {
			currentResultNode = currentResultNode.getResult(naviNode);
		}
	}

	protected void transformPathToNodeList(String path) {
		transformPathToSegmentList(path);
		transformSegmentListToNodeList(path);
	}
	
	protected void transformPathToSegmentList(String path) {
		segmentList.clear();
		StringUtil.splitStr(path, clawSetting.getSetting("path.delimiter"), segmentList);
	}
	
	protected void transformSegmentListToNodeList(String path) {
		naviNodeList.clear();
		for (String segment : segmentList) {
			transformSegmentToNode(segment);
		}
	}
	
	protected void transformSegmentToNode(String segment) {
		NaviNodeAnalyzer analyzer = getNaviNodeAnalyzer(segment);
		naviNodeList.add(analyzer.analyze(segment));
	}
	
	protected NaviNodeAnalyzer getNaviNodeAnalyzer(String segment) {
		if (segment.startsWith(clawSetting.getSetting("index.flag"))) {
			return naviNodeAnalyzers.get("index");
		}
		return naviNodeAnalyzers.get("name");
	}
	
	protected void registerNaviNodeAnalyzer() {
		naviNodeAnalyzers.put("name", new NameNodeAnalyzer(clawSetting.getSetting("index.flag"), clawSetting.getSetting("depth.flag")));
		naviNodeAnalyzers.put("index", new IndexNodeAnalyzer(clawSetting.getSetting("index.flag"), clawSetting.getSetting("depth.flag")));
	}
	
}
