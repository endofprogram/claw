package org.eop.claw.exception;

/**
 * @author lixinjie
 * @since 2017-06-05
 */
public class ResultException extends ClawException {

	private static final long serialVersionUID = 765797276283507819L;

	public ResultException(String message) {
		super(message);
	}
	
	public ResultException(Throwable cause) {
		super(cause);
	}
	
	public ResultException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ResultException(String message, String segment) {
		super(message, segment);
	}

}
