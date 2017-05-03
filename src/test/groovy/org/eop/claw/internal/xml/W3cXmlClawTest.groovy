package org.eop.claw.internal.xml

import javax.xml.parsers.DocumentBuilderFactory

import org.eop.chassis.test.AbstractGroovyTest
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.w3c.dom.Element
import org.xml.sax.InputSource

/**
 * @author lixinjie
 */
class W3cXmlClawTest extends AbstractGroovyTest {

	W3cXmlClaw claw
	
	@Before
	void init() {
		claw = new W3cXmlClaw(DocumentBuilderFactory.newInstance().newDocumentBuilder()
			.parse(new InputSource(new StringReader('''\
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
</response>'''))).getDocumentElement())
	}
	
	@Test
	void testGet1() {
		Assert.assertEquals("0", claw.get("res_code<>"))
	}
	
	@Test
	void testGet2() {
		Assert.assertEquals("Processing the request succeeded!", claw.get("res_desc<>"))
	}
	
	@Test
	void testGet3() {
		Assert.assertThat(claw.get("result{}"), Matchers.isA(Element.class))
	}
	
	@Test
	void testGet4() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[]"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet5() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[].0*4()"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet6() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[].0*4().0+-1()"), Matchers.isA(List.class))
	}
	
	@Test
	void testGet7() {
		Assert.assertThat(claw.get("result{}.MEALINFOUPLIST[].0*4().0+-1().1()"), Matchers.isA(String.class))
	}
	
	@Test
	void testGet8() {
		Assert.assertEquals("4444", claw.get("result{}.MEALINFOUPLIST[].0*4().0+-1().1()"))
	}
	
	@Test
	void testGet9() {
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