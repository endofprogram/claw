package org.eop.claw.node.analyzer.exception;

import org.eop.claw.exception.ClawException;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class NaviNodeAnalyzerException extends ClawException {

	private static final long serialVersionUID = 8350044445458728533L;

	public NaviNodeAnalyzerException(String message) {
		super(message);
	}
	
	public NaviNodeAnalyzerException(Throwable cause) {
		super(cause);
	}
	
	public NaviNodeAnalyzerException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NaviNodeAnalyzerException(String message, String segment) {
		super(message, segment);
	}

}
