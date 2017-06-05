package org.eop.claw;

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
	
	public Claw(Map<String, Object> map, ClawSetting clawSetting) {
		this(new MapClaw(map, clawSetting));
	}
	
	public Claw(net.sf.json.JSONObject jsonObject) {
		this(new NetsfJsonClaw(jsonObject));
	}
	
	public Claw(net.sf.json.JSONObject jsonObject, ClawSetting clawSetting) {
		this(new NetsfJsonClaw(jsonObject, clawSetting));
	}
	
	public Claw(com.alibaba.fastjson.JSONObject jsonObject) {
		this(new FastJsonClaw(jsonObject));
	}
	
	public Claw(com.alibaba.fastjson.JSONObject jsonObject, ClawSetting clawSetting) {
		this(new FastJsonClaw(jsonObject, clawSetting));
	}
	
	public Claw(org.dom4j.Element element) {
		this(new Dom4jXmlClaw(element));
	}
	
	public Claw(org.dom4j.Element element, ClawSetting clawSetting) {
		this(new Dom4jXmlClaw(element, clawSetting));
	}
	
	public Claw(org.w3c.dom.Element element) {
		this(new W3cXmlClaw(element));
	}
	
	public Claw(org.w3c.dom.Element element, ClawSetting clawSetting) {
		this(new W3cXmlClaw(element, clawSetting));
	}
	
	protected Claw(IClaw claw) {
		this.claw = claw;
	}
	
	@Override
	public IResult getResult(String path) {
		return claw.getResult(path);
	}

	@Override
	public Object getUnderlyingData() {
		return claw.getUnderlyingData();
	}
}
