package org.eop.claw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eop.claw.internal.json.FastJsonClaw;
import org.eop.claw.internal.json.NetsfJsonClaw;
import org.eop.claw.internal.map.MapClaw;
import org.eop.claw.internal.xml.Dom4jXmlClaw;
import org.eop.claw.internal.xml.W3cXmlClaw;
/**
 * lixinjie 2016-12-26
 */
public class Claw implements IClaw {

	protected IClaw claw;
	
	public Claw(Map<String, Object> map) {
		this(new MapClaw(map));
	}
	
	public Claw(net.sf.json.JSONObject jsonObject) {
		this(new NetsfJsonClaw(jsonObject));
	}
	
	public Claw(com.alibaba.fastjson.JSONObject jsonObject) {
		this(new FastJsonClaw(jsonObject));
	}
	
	public Claw(org.dom4j.Element element) {
		this(new Dom4jXmlClaw(element));
	}
	
	public Claw(org.w3c.dom.Element element) {
		this(new W3cXmlClaw(element));
	}
	
	protected Claw(IClaw claw) {
		this.claw = claw;
	}
	
	@Override
	public BigDecimal getBigDecimal(String path) {
		return claw.getBigDecimal(path);
	}

	@Override
	public BigInteger getBigInteger(String path) {
		return claw.getBigInteger(path);
	}

	@Override
	public Boolean getBoolean(String path) {
		return claw.getBoolean(path);
	}

	@Override
	public Byte getByte(String path) {
		return claw.getByte(path);
	}

	@Override
	public Date getDate(String path) {
		return claw.getDate(path);
	}

	@Override
	public Double getDouble(String path) {
		return claw.getDouble(path);
	}

	@Override
	public Float getFloat(String path) {
		return claw.getFloat(path);
	}

	@Override
	public Integer getInteger(String path) {
		return claw.getInteger(path);
	}

	@Override
	public Long getLong(String path) {
		return claw.getLong(path);
	}

	@Override
	public Short getShort(String path) {
		return claw.getShort(path);
	}

	@Override
	public String getString(String path) {
		return claw.getString(path);
	}

	@Override
	public Timestamp getTimestamp(String path) {
		return claw.getTimestamp(path);
	}
	
	@Override
	public <T> List<T> getList(String path) {
		return claw.getList(path);
	}

	@Override
	public Object get(String path) {
		return claw.get(path);
	}

	@Override
	public <T> T get(String path, Class<T> targetClass) {
		return claw.get(path, targetClass);
	}
	
	@Override
	public IClaw getClaw(String path) {
		return new Claw(claw.getClaw(path));
	}

	@Override
	public Object getUnderlyingData() {
		return claw.getUnderlyingData();
	}
}
