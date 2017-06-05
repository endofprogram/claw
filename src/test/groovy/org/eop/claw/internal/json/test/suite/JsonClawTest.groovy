package org.eop.claw.internal.json.test.suite

import org.eop.claw.internal.json.test.FastJsonClawTest
import org.eop.claw.internal.json.test.NetsfJsonClawTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

/**
 * @author lixinjie
 * @since 2017-06-05
 */
@RunWith(Suite.class)
@SuiteClasses([FastJsonClawTest.class, NetsfJsonClawTest.class])
class JsonClawTest {

}
