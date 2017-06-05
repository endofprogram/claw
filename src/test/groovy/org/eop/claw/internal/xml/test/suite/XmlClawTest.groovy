package org.eop.claw.internal.xml.test.suite

import org.eop.claw.internal.xml.test.Dom4jXmlClawTest
import org.eop.claw.internal.xml.test.W3cXmlClawTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

/**
 * @author lixinjie
 * @since 2017-06-05
 */
@RunWith(Suite.class)
@SuiteClasses([Dom4jXmlClawTest.class, W3cXmlClawTest.class])
class XmlClawTest {

}
