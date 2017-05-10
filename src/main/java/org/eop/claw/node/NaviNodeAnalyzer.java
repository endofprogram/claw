package org.eop.claw.node;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public interface NaviNodeAnalyzer {

	String getIndexFlag();
	void setIndexFlag(String indexFlag);
	
	String getDepthFlag();
	void setDepthFlag(String depthFlag);
	
	NaviNode analyze(String segment);
}
