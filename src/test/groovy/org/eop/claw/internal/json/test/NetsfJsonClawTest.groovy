package org.eop.claw.internal.json.test

import org.eop.chassis.test.AbstractCommonTest
import org.eop.claw.internal.json.NetsfJsonClaw
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import net.sf.json.JSONArray
import net.sf.json.JSONObject

/**
 * @author lixinjie
 */
class NetsfJsonClawTest extends AbstractCommonTest {

	NetsfJsonClaw claw
	
	@Before
	void init() {
		claw = new NetsfJsonClaw(JSONObject.fromObject('{"res_code":"0","res_desc":"Processing the request succeeded!","result":{"MEALINFOINLIST":[{"Col_1":"008W","Col_2":"和校园赠20分钟本地主叫","Col_3":"01","Col_4":"01","Col_5":"20","Col_6":"4","Col_7":"16","Col_8":"和校园赠送20分钟本地主叫","Col_9":"201612","Col_10":"201612","Col_11":"1","Col_12":"","Col_13":"20120903","Col_14":"0","Col_15":"20161231235959","Col_16":"20161231235959","Col_17":"02","Col_18":"02","Col_19":"2016-12-02 10:14:30"},{"Col_1":"06HC","Col_2":"石家庄2013畅聊卡","Col_3":"03","Col_4":"02","Col_5":"5","Col_6":"0","Col_7":"5","Col_8":"石家庄2013畅聊卡1元彩信包","Col_9":"201612","Col_10":"201612","Col_11":"1","Col_12":"","Col_13":"20150401","Col_14":"0","Col_15":"20161231235959","Col_16":"20161231235959","Col_17":"02","Col_18":"02","Col_19":"2016-12-02 10:14:30"},{"Col_1":"06HC","Col_2":"石家庄2013畅聊卡","Col_3":"02","Col_4":"02","Col_5":"20","Col_6":"1","Col_7":"19","Col_8":"石家庄2013畅聊卡2元短信包","Col_9":"201612","Col_10":"201612","Col_11":"1","Col_12":"","Col_13":"20150401","Col_14":"0","Col_15":"20161231235959","Col_16":"20161231235959","Col_17":"02","Col_18":"02","Col_19":"2016-12-02 10:14:30"},{"Col_1":"06HC","Col_2":"石家庄2013畅聊卡","Col_3":"04","Col_4":"03","Col_5":"30720","Col_6":"7303","Col_7":"23417","Col_8":"石家庄2013畅聊卡5元流量包","Col_9":"201612","Col_10":"201612","Col_11":"1","Col_12":"国内2/3/4G融合流量","Col_13":"20150401","Col_14":"2","Col_15":"20161231235959","Col_16":"20170131235959","Col_17":"01","Col_18":"01","Col_19":"2016-12-02 10:14:30"}],"MEALINFOUPLIST":[{"Col_1":"04","Col_2":"0","Col_3":"03","Col_4":"02","Col_5":"0.00","Col_6":"2016-12-02 10:14:30"},{"Col_1":"05","Col_2":"0","Col_3":"01","Col_4":"05","Col_5":"0.00","Col_6":"2016-12-02 10:14:30"},{"Col_1":"05","Col_2":"0","Col_3":"03","Col_4":"05","Col_5":"0.00","Col_6":"2016-12-02 10:14:30"}],"MEALINFOGPRSLIST":[{"Col_1":"国内2/3/4G融合流量","Col_2":"30720","Col_3":"7303","Col_4":"23417","Col_5":"03","Col_6":"2016-12-02 10:14:30"}]}}'))
	}
	
	@Test
	void testGet01() {
		Assert.assertEquals(0, claw.getResult("res_code<>").getInteger())
	}
	
	@Test
	void testGet02() {
		Assert.assertEquals("Processing the request succeeded!", claw.getResult("res_desc<>").getString())
	}
	
	@Test
	void testGet03() {
		Assert.assertThat(claw.getResult("result{}").getValue(), Matchers.isA(JSONObject.class))
	}
	
	@Test
	void testGet04() {
		Assert.assertThat(claw.getResult("result{}").getValue().getClass(), Matchers.theInstance(JSONObject.class))
	}
	
	@Test
	void testGet05() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST[]").getValue(), Matchers.isA(JSONArray.class))
	}
	
	@Test
	void testGet06() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOUPLIST[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet07() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOGPRSLIST[]").getValue(), Matchers.isA(JSONArray.class))
	}
	
	@Test
	void testGet08() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST[].#0{}").getValue(), Matchers.isA(JSONObject.class))
	}
	
	@Test
	void testGet09() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST[].#-1<>").getValue(), Matchers.isA(JSONObject.class))
	}
	
	@Test
	void testGet10() {
		Assert.assertEquals("2016-12-02 10:14:30", claw.getResult("result{}.MEALINFOINLIST[].#0{}.Col_19<>").getString())
	}
	
	@Test
	void testGet11() {
		Assert.assertEquals("石家庄2013畅聊卡5元流量包", claw.getResult("result{}.MEALINFOINLIST[].#-1{}.Col_8<>").getString())
	}
	
	@Test
	void testGet12() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST[].#0*-1[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet13() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST[].#0*-1[].#1+3[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet14() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST[].#0*-1[].#1+2[].#-1<>").getValue(), Matchers.isA(JSONObject.class))
	}
	
	@Test
	void testGet15() {
		Assert.assertEquals("石家庄2013畅聊卡2元短信包", claw.getResult("result{}.MEALINFOINLIST[].#0*-1[].#1+2[].#-1{}.Col_8<>").getString())
	}
	
	@Test
	void testGet16() {
		Assert.assertEquals("石家庄2013畅聊卡2元短信包", claw.getResult("result{}.MEALINFOINLIST[].#0*-1[].#1+2[].#1{}.Col_8<>").getString())
	}
}
