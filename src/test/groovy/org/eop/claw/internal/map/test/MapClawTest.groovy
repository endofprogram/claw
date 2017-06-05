package org.eop.claw.internal.map.test

import org.eop.chassis.test.AbstractCommonTest
import org.eop.claw.internal.map.MapClaw
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author lixinjie
 */
class MapClawTest extends AbstractCommonTest {

	MapClaw claw
	
	@Before
	void init() {
		def map = [res_code:'0', res_desc:'Processing the request succeeded!', result:[MEALINFOINLIST:[[Col_1:'008W', Col_2:'和校园赠20分钟本地主叫'], [Col_1:'06HC', Col_2:'石家庄2011畅聊卡'], [Col_1:'008W', Col_2:'石家庄2012畅聊卡'], [Col_1:'06HC', Col_2:'石家庄2013畅聊卡']], MEALINFOUPLIST:["1111", "2222", "3333", "4444"], MEALINFOGPRSLIST:[Col_1:'1', Col_2:'2', Col_3:'3', Col_4:'4'], list:[[[a:"a", b:'a'], [a:"b", b:'b']], [[a:"c", b:'c'], [a:"d", b:'d']]], lists:[[[[[a:"1a", b:'1a'], [a:"2b", b:'2b']], [[a:"1c", b:'1c'], [a:"2d", b:'2d']]], [[[a:"1e", b:'1e'], [a:"2f", b:'2f']], [[a:"1g", b:'1g'], [a:"2h", b:'2h']]]], [[[[a:"3i", b:'3i'], [a:"4j", b:'4j']], [[a:"3k", b:'3k'], [a:"4l", b:'4l']]], [[[a:"3m", b:'3m'], [a:"4n", b:'4n']], [[a:"3o", b:'3o'], [a:"4p", b:'4p']]]]]]]
		claw = new MapClaw(map)
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
		Assert.assertEquals('[MEALINFOINLIST:[[Col_1:008W, Col_2:和校园赠20分钟本地主叫], [Col_1:06HC, Col_2:石家庄2011畅聊卡], [Col_1:008W, Col_2:石家庄2012畅聊卡], [Col_1:06HC, Col_2:石家庄2013畅聊卡]], MEALINFOUPLIST:[1111, 2222, 3333, 4444], MEALINFOGPRSLIST:[Col_1:1, Col_2:2, Col_3:3, Col_4:4], list:[[[a:a, b:a], [a:b, b:b]], [[a:c, b:c], [a:d, b:d]]], lists:[[[[[a:1a, b:1a], [a:2b, b:2b]], [[a:1c, b:1c], [a:2d, b:2d]]], [[[a:1e, b:1e], [a:2f, b:2f]], [[a:1g, b:1g], [a:2h, b:2h]]]], [[[[a:3i, b:3i], [a:4j, b:4j]], [[a:3k, b:3k], [a:4l, b:4l]]], [[[a:3m, b:3m], [a:4n, b:4n]], [[a:3o, b:3o], [a:4p, b:4p]]]]]]', claw.getResult("result{}").getValue().toString())
	}
	
	@Test
	void testGet04() {
		Assert.assertEquals('[1111, 2222, 3333, 4444]', claw.getResult("result{}.MEALINFOUPLIST[]").getValue().toString())
	}
	
	@Test
	void testGet05() {
		Assert.assertEquals('[1111, 2222, 3333, 4444]', claw.getResult("result{}.MEALINFOUPLIST[].#0*4[]").getValue().toString())
	}
	
	@Test
	void testGet06() {
		Assert.assertEquals('[1111, 4444]', claw.getResult("result{}.MEALINFOUPLIST[].#0*4[].#0+-1[]").getValue().toString())
	}
	
	@Test
	void testGet07() {
		Assert.assertEquals(4444, claw.getResult("result{}.MEALINFOUPLIST[].#0*4[].#0+-1[].#1<>").getInteger())
	}
	
	@Test
	void testGet08() {
		Assert.assertEquals('1111', claw.getResult("result{}.MEALINFOUPLIST[].#0*4[].#0+-1[].#-2<>").getValue())
	}
	
	@Test
	void testGet09() {
		Assert.assertEquals('[[Col_1:008W, Col_2:和校园赠20分钟本地主叫], [Col_1:06HC, Col_2:石家庄2011畅聊卡], [Col_1:008W, Col_2:石家庄2012畅聊卡], [Col_1:06HC, Col_2:石家庄2013畅聊卡]]', claw.getResult("result{}.MEALINFOINLIST[]").getValue().toString())
	}
	
	@Test
	void testGet10() {
		Assert.assertEquals('[[Col_1:008W, Col_2:石家庄2012畅聊卡], [Col_1:06HC, Col_2:石家庄2013畅聊卡]]', claw.getResult("result{}.MEALINFOINLIST[].#2+3[]").getValue().toString())
	}
	
	@Test
	void testGet11() {
		Assert.assertEquals('[Col_1:06HC, Col_2:石家庄2013畅聊卡]', claw.getResult("result{}.MEALINFOINLIST[].#2+3[].#-1<>").getValue().toString())
	}
	
	@Test
	void testGet12() {
		Assert.assertEquals('石家庄2013畅聊卡', claw.getResult("result{}.MEALINFOINLIST[].#2+3[].#-1{}.Col_2<>").getString())
	}
	
	@Test
	void testGet13() {
		Assert.assertEquals('06HC', claw.getResult("result{}.MEALINFOINLIST[].#2+3[].#1{}.Col_1<>").getString())
	}
	
	@Test
	void testGet14() {
		Assert.assertEquals('[Col_1:1, Col_2:2, Col_3:3, Col_4:4]', claw.getResult("result{}.MEALINFOGPRSLIST{}").getValue().toString())
	}
	
	@Test
	void testGet15() {
		Assert.assertEquals('3', claw.getResult("result{}.MEALINFOGPRSLIST{}.Col_3<>").getString())
	}
	
	@Test
	void testGet16() {
		Assert.assertEquals('a', claw.getResult("result{}.list[].#0[].#0{}.a<>").getString())
	}
	
	@Test
	void testGet17() {
		Assert.assertEquals('[和校园赠20分钟本地主叫, 石家庄2011畅聊卡, 石家庄2012畅聊卡, 石家庄2013畅聊卡]', claw.getResult("result{}.MEALINFOINLIST[].Col_2[]").getValue().toString())
	}
	
	@Test
	void testGet18() {
		Assert.assertEquals('[008W, 06HC, 008W, 06HC]', claw.getResult("result{}.MEALINFOINLIST[].Col_1[]").getValue().toString())
	}
	
	@Test
	void testGet19() {
		Assert.assertEquals('008W', claw.getResult("result{}.MEALINFOINLIST[].Col_1[].#-4<>").getString())
	}
	
	@Test
	void testGet20() {
		Assert.assertEquals('[[a:a, b:a], [a:c, b:c]]', claw.getResult("result{}.list[].#0!1[]").getValue().toString())
	}
	
	@Test
	void testGet21() {
		Assert.assertEquals('[[a:b, b:b], [a:d, b:d]]', claw.getResult("result{}.list[].#-1!1[]").getValue().toString())
	}
	
	@Test
	void testGet22() {
		Assert.assertEquals('[[a, b], [c, d]]', claw.getResult("result{}.list[].a!1[]").getValue().toString())
	}
	
	@Test
	void testGet23() {
		Assert.assertEquals('[[a, b], [c, d]]', claw.getResult("result{}.list[].b!1[]").getValue().toString())
	}
	
	@Test
	void testGet24() {
		Assert.assertEquals('[[[[[a:1a, b:1a], [a:2b, b:2b]], [[a:1c, b:1c], [a:2d, b:2d]]], [[[a:1e, b:1e], [a:2f, b:2f]], [[a:1g, b:1g], [a:2h, b:2h]]]], [[[[a:3i, b:3i], [a:4j, b:4j]], [[a:3k, b:3k], [a:4l, b:4l]]], [[[a:3m, b:3m], [a:4n, b:4n]], [[a:3o, b:3o], [a:4p, b:4p]]]]]', claw.getResult("result{}.lists[]").getValue().toString())
	}
	
	@Test
	void testGet25() {
		Assert.assertEquals('[[[[a:1a, b:1a], [a:2b, b:2b]], [[a:1c, b:1c], [a:2d, b:2d]]], [[[a:1e, b:1e], [a:2f, b:2f]], [[a:1g, b:1g], [a:2h, b:2h]]]]', claw.getResult("result{}.lists[].#0[]").getValue().toString())
	}
	
	@Test
	void testGet26() {
		Assert.assertEquals('[[[[a:3i, b:3i], [a:4j, b:4j]], [[a:3k, b:3k], [a:4l, b:4l]]], [[[a:3m, b:3m], [a:4n, b:4n]], [[a:3o, b:3o], [a:4p, b:4p]]]]', claw.getResult("result{}.lists[].#1[]").getValue().toString())
	}
	
	@Test
	void testGet27() {
		Assert.assertEquals('[[[[a:1a, b:1a], [a:2b, b:2b]], [[a:1c, b:1c], [a:2d, b:2d]]], [[[a:3i, b:3i], [a:4j, b:4j]], [[a:3k, b:3k], [a:4l, b:4l]]]]', claw.getResult("result{}.lists[].#0!1[]").getValue().toString())
	}
	
	@Test
	void testGet28() {
		Assert.assertEquals('[[[[a:1a, b:1a], [a:2b, b:2b]], [[a:1e, b:1e], [a:2f, b:2f]]], [[[a:3i, b:3i], [a:4j, b:4j]], [[a:3m, b:3m], [a:4n, b:4n]]]]', claw.getResult("result{}.lists[].#0!2[]").getValue().toString())
	}
	
	@Test
	void testGet29() {
		Assert.assertEquals('[[[[a:1a, b:1a], [a:1c, b:1c]], [[a:1e, b:1e], [a:1g, b:1g]]], [[[a:3i, b:3i], [a:3k, b:3k]], [[a:3m, b:3m], [a:3o, b:3o]]]]', claw.getResult("result{}.lists[].#0!3[]").getValue().toString())
	}
	
	@Test
	void testGet30() {
		Assert.assertEquals('[[[[1a, 2b], [1c, 2d]], [[1e, 2f], [1g, 2h]]], [[[3i, 4j], [3k, 4l]], [[3m, 4n], [3o, 4p]]]]', claw.getResult("result{}.lists[].a!3[]").getValue().toString())
	}
	
	@Test
	void testGet31() {
		Assert.assertEquals('[[[[1a, 2b], [1c, 2d]], [[1e, 2f], [1g, 2h]]], [[[3i, 4j], [3k, 4l]], [[3m, 4n], [3o, 4p]]]]', claw.getResult("result{}.lists[].b!3[]").getValue().toString())
	}
	
	@Test
	void testGet32() {
		Assert.assertEquals('[[[[a:1e, b:1e], [a:2f, b:2f]], [[a:1g, b:1g], [a:2h, b:2h]]], [[[a:3m, b:3m], [a:4n, b:4n]], [[a:3o, b:3o], [a:4p, b:4p]]]]', claw.getResult("result{}.lists[].#-1!1[]").getValue().toString())
	}
	
	@Test
	void testGet33() {
		Assert.assertEquals('[[[[a:1c, b:1c], [a:2d, b:2d]], [[a:1g, b:1g], [a:2h, b:2h]]], [[[a:3k, b:3k], [a:4l, b:4l]], [[a:3o, b:3o], [a:4p, b:4p]]]]', claw.getResult("result{}.lists[].#-1!2[]").getValue().toString())
	}
	
	@Test
	void testGet34() {
		Assert.assertEquals('[[[[a:2b, b:2b], [a:2d, b:2d]], [[a:2f, b:2f], [a:2h, b:2h]]], [[[a:4j, b:4j], [a:4l, b:4l]], [[a:4n, b:4n], [a:4p, b:4p]]]]', claw.getResult("result{}.lists[].#-1!3[]").getValue().toString())
	}
}
