package org.eop.claw.internal.xml

import org.dom4j.DocumentHelper
import org.dom4j.Element
import org.eop.chassis.test.AbstractGroovyTest
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author lixinjie
 */
class Dom4jXmlClawTest extends AbstractGroovyTest {

	Dom4jXmlClaw claw
	
	@Before
	void init() {
		claw = new Dom4jXmlClaw(DocumentHelper.parseText('''\
<response>
<res_code>0</res_code>
<res_desc>Processing the request succeeded!</res_desc>
<result>
<MEALINFOINLIST>
<MEALINFOIN>
<Col_1>008W</Col_1><Col_2>和校园赠20分钟本地主叫</Col_2>
</MEALINFOIN>
<MEALINFOIN>
<Col_1>06HC</Col_1><Col_2>石家庄2011畅聊卡</Col_2>
</MEALINFOIN>
<MEALINFOIN>
<Col_1>008W</Col_1><Col_2>石家庄2012畅聊卡</Col_2>
</MEALINFOIN>
<MEALINFOIN>
<Col_1>06HC</Col_1><Col_2>石家庄2013畅聊卡</Col_2>
</MEALINFOIN>
</MEALINFOINLIST>
<MEALINFOUPLIST>
<Col>1111</Col>
<Col>2222</Col>
<Col>3333</Col>
<Col>4444</Col>
</MEALINFOUPLIST>
<MEALINFOGPRSLIST>
<Col_1>1</Col_1>
<Col_2>2</Col_2>
<Col_3>3</Col_3>
<Col_4>4</Col_4>
</MEALINFOGPRSLIST>
</result>
</response>''').getRootElement())
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
		Assert.assertThat(claw.get("result{}"), Matchers.isA(Element.class))
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
		Assert.assertThat(claw.get("result{}.MEALINFOINLIST[].2+3().-1()"), Matchers.isA(Element.class))
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
		Assert.assertThat(claw.get("result{}.MEALINFOGPRSLIST{}"), Matchers.isA(Element.class))
	}
	
	@Test
	void testGet15() {
		Assert.assertEquals("3", claw.get("result{}.MEALINFOGPRSLIST{}.Col_3<>"))
	}
}