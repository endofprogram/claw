package org.eop.claw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eop.chassis.util.StringUtil;
import org.eop.chassis.util.TypeUtil;
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
	public BigDecimal getBigDecimal(String path) {
		return TypeUtil.toBigDecimal(get(path));
	}

	@Override
	public BigInteger getBigInteger(String path) {
		return TypeUtil.toBigInteger(get(path));
	}

	@Override
	public Boolean getBoolean(String path) {
		return TypeUtil.toBoolean(get(path));
	}

	@Override
	public Byte getByte(String path) {
		return TypeUtil.toByte(get(path));
	}

	@Override
	public Date getDate(String path) {
		return TypeUtil.toDate(get(path));
	}

	@Override
	public Double getDouble(String path) {
		return TypeUtil.toDouble(get(path));
	}

	@Override
	public Float getFloat(String path) {
		return TypeUtil.toFloat(get(path));
	}

	@Override
	public Integer getInteger(String path) {
		return TypeUtil.toInteger(get(path));
	}

	@Override
	public Long getLong(String path) {
		return TypeUtil.toLong(get(path));
	}

	@Override
	public Short getShort(String path) {
		return TypeUtil.toShort(get(path));
	}

	@Override
	public String getString(String path) {
		return TypeUtil.toString(get(path));
	}

	@Override
	public Timestamp getTimestamp(String path) {
		return TypeUtil.toTimestamp(get(path));
	}
	
	@Override
	public <T> List<T> getList(String path) {
		return TypeUtil.asListType(get(path));
	}

	@Override
	public Object get(String path) {
		crawlResultNode(path);
		return currentResultNode.getValue();
	}

	@Override
	public abstract <T> T get(String path, Class<T> targetClass);

	@Override
	public abstract IClaw getClaw(String path);
	
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
