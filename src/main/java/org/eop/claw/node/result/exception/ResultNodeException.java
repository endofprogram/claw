package org.eop.claw.node.result.exception;

import org.eop.claw.exception.ClawException;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class ResultNodeException extends ClawException {

	private static final long serialVersionUID = -1102199915742309420L;

	public ResultNodeException(String message) {
		super(message);
	}
	
	public ResultNodeException(Throwable cause) {
		super(cause);
	}
	
	public ResultNodeException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
