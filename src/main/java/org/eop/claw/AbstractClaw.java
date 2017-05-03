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
import org.eop.claw.node.RNode;
import org.eop.claw.node.WNode;
import org.eop.claw.node.WNodeAnalyzer;
import org.eop.claw.node.analyzer.ElementNodeAnalyzer;
import org.eop.claw.node.analyzer.IndexNodeAnalyzer;
import org.eop.claw.node.analyzer.ListNodeAnalyzer;
import org.eop.claw.node.analyzer.MapNodeAnalyzer;
/**
 * lixinjie 2016-12-26
 */
public abstract class AbstractClaw implements IClaw {

	protected List<WNode> wnodeList = new ArrayList<>();
	protected List<String> segmentList = new ArrayList<>();
	protected Map<String, WNodeAnalyzer> wnodeAnalyzers = new HashMap<>();
	protected RNode rootRNode;
	protected RNode currentRNode;
	
	protected AbstractClaw(RNode rootRNode) {
		this.rootRNode = rootRNode;
		registerWNodeAnalyzer();
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
		crawlRNode(path);
		return currentRNode.getValue();
	}

	@Override
	public abstract <T> T get(String path, Class<T> targetClass);

	@Override
	public abstract IClaw getClaw(String path);
	
	@Override
	public Object getUnderlyingData() {
		return rootRNode.getValue();
	}
	
	protected void crawlRNode(String path) {
		transformPathToNodeList(path);
		currentRNode = rootRNode;
		for (WNode wnode : wnodeList) {
			currentRNode = currentRNode.getResult(wnode);
		}
	}

	protected void transformPathToNodeList(String path) {
		transformPathToSegmentList(path);
		transformSegmentListToNodeList(path);
	}
	
	protected void transformPathToSegmentList(String path) {
		segmentList.clear();
		StringUtil.splitStr(path, ".", segmentList);
	}
	
	protected void transformSegmentListToNodeList(String path) {
		wnodeList.clear();
		for (String segment : segmentList) {
			transformSegmentToNode(segment);
		}
	}
	
	protected void transformSegmentToNode(String segment) {
		WNodeAnalyzer analyzer = wnodeAnalyzers.get(StringUtil.getLast(segment));
		wnodeList.add(analyzer.analyze(segment));
	}
	
	protected void registerWNodeAnalyzer() {
		wnodeAnalyzers.put("}", new MapNodeAnalyzer());
		wnodeAnalyzers.put("]", new ListNodeAnalyzer());
		wnodeAnalyzers.put(")", new IndexNodeAnalyzer());
		wnodeAnalyzers.put(">", new ElementNodeAnalyzer());
	}
}
