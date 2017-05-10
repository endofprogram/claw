package org.eop.claw.exception;

/**
 * @author lixinjie
 * @since 2017-05-10
 */
public class ClawException extends RuntimeException {

	private static final long serialVersionUID = -6304654493313388193L;
	
	public ClawException(String message, String segment) {
		super(generateMessage(message, segment));
	}

	protected static String generateMessage(String message, String segment) {
		return message + segment;
	}
}
