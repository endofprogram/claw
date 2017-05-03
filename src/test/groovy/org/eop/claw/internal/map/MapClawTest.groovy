package org.eop.claw.internal.map

import org.eop.chassis.test.AbstractGroovyTest
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author lixinjie
 */
class MapClawTest extends AbstractGroovyTest {

	MapClaw claw
	
	@Before
	void init() {
		def map = [res_code:'0', res_desc:'Processing the request succeeded!', result:[MEALINFOINLIST:[[Col_1:'008W', Col_2:'和校园赠20分钟本地主叫'], [Col_1:'06HC', Col_2:'石家庄2011畅聊卡'], [Col_1:'008W', Col_2:'石家庄2012畅聊卡'], [Col_1:'06HC', Col_2:'石家庄2013畅聊卡']], MEALINFOUPLIST:["1111", "2222", "3333", "4444"], MEALINFOGPRSLIST:[Col_1:'1', Col_2:'2', Col_3:'3', Col_4:'4'], list:[[[a:"a", b:'a'], [a:"b", b:'b']], [[a:"c", b:'c'], [a:"c", b:'c']]]]]
		claw = new MapClaw(map)
	}
	
	@Test
	void testGet01() {
		Assert.assertEquals("0", claw.get("res_code<>"))
	}
	
	@Test
	void testGet02() {
		Assert.assertEquals("Processing the request succeeded!", claw.get("res_desc<>"))
	}
	
	@Test
	void testGet03() {
		Assert.assertThat(claw.get("result{}"), Matchers.instanceOf(Map.class))
	}
	
	@Test
	void testGet04() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[]"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet05() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[].0*4()"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet06() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[].0*4().0+-1()"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet07() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[].0*4().0+-1().1()"), Matchers.isA(String.class))
	}
	
	@Test
	void testGet08() {
		Assert.assertEquals("4444", claw.get("result{}.MEALINFOUPLIST[].0*4().0+-1().1()"))
	}
	
	@Test
	void testGet09() {
		Assert.assertThat(claw.get("result{}.MEALINFOINLIST[]"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet10() {
		Assert.assertThat(claw.get("result{}.MEALINFOINLIST[].2+3()"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet11() {
		Assert.assertThat(claw.get("result{}.MEALINFOINLIST[].2+3().-1()"), Matchers.isA(Map.class))
	}
	
	@Test
	void testGet12() {
		Assert.assertEquals("石家庄2013畅聊卡", claw.get("result{}.MEALINFOINLIST[].2+3().-1().Col_2<>"))
	}
	
	@Test
	void testGet13() {
		Assert.assertEquals("06HC", claw.get("result{}.MEALINFOINLIST[].2+3().-1().Col_1<>"))
	}
	
	@Test
	void testGet14() {
		Assert.assertThat(claw.get("result{}.MEALINFOGPRSLIST{}"), Matchers.isA(Map.class))
	}
	
	@Test
	void testGet15() {
		Assert.assertEquals("3", claw.get("result{}.MEALINFOGPRSLIST{}.Col_3<>"))
	}
	
	@Test
	void testGet16() {
		Assert.assertEquals("a", claw.get("result{}.list[].0().0().a<>"))
	}
}
