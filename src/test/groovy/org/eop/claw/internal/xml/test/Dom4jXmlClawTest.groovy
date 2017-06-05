package org.eop.claw.internal.xml.test

import org.dom4j.DocumentHelper
import org.dom4j.Element
import org.eop.chassis.test.AbstractCommonTest
import org.eop.claw.internal.xml.Dom4jXmlClaw
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author lixinjie
 */
class Dom4jXmlClawTest extends AbstractCommonTest {

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
		Assert.assertEquals(0, claw.getResult("res_code{}.text<>").getInteger())
	}
	
	@Test
	void testGet02() {
		Assert.assertEquals("Processing the request succeeded!", claw.getResult("res_desc{}.text<>").getString())
	}
	
	@Test
	void testGet03() {
		Assert.assertThat(claw.getResult("result{}").getValue(), Matchers.isA(Element.class))
	}
	
	@Test
	void testGet04() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOUPLIST{}.Col[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet05() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOUPLIST{}.Col[].#0*4[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet06() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOUPLIST{}.Col[].#0*4[].#0+-1[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet07() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOUPLIST{}.Col[].#0*4[].#0+-1[].#1{}.text<>").getString(), Matchers.isA(String.class))
	}
	
	@Test
	void testGet08() {
		Assert.assertEquals("4444", claw.getResult("result{}.MEALINFOUPLIST{}.Col[].#0*4[].#0+-1[].#1{}.text<>").getString())
	}
	
	@Test
	void testGet09() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST{}.MEALINFOIN[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet10() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST{}.MEALINFOIN[].#2+3[]").getValue(), Matchers.isA(List.class))
	}
	
	@Test
	void testGet11() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOINLIST{}.MEALINFOIN[].#2+3[].#-1{}").getValue(), Matchers.isA(Element.class))
	}
	
	@Test
	void testGet12() {
		Assert.assertEquals("石家庄2013畅聊卡", claw.getResult("result{}.MEALINFOINLIST{}.MEALINFOIN[].#2+3[].#-1{}.Col_2{}.text<>").getString())
	}
	
	@Test
	void testGet13() {
		Assert.assertEquals("06HC", claw.getResult("result{}.MEALINFOINLIST{}.MEALINFOIN[].#2+3[].#-1{}.Col_1{}.text<>").getString())
	}
	
	@Test
	void testGet14() {
		Assert.assertThat(claw.getResult("result{}.MEALINFOGPRSLIST{}").getValue(), Matchers.isA(Element.class))
	}
	
	@Test
	void testGet15() {
		Assert.assertEquals(3, claw.getResult("result{}.MEALINFOGPRSLIST{}.Col_3{}.text<>").getInteger())
	}
}