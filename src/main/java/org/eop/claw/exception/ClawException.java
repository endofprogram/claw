package org.eop.claw.exception;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class ClawException extends RuntimeException {

	private static final long serialVersionUID = -6304654493313388193L;
	
	public ClawException(String message) {
		super(message);
	}
	
	public ClawException(Throwable cause) {
		super(cause);
	}
	
	public ClawException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
