package com.yoti.sample.hoover.clean.common.constants;

/**
 * Constants for the Exceptions
 *
 * @author Artem Lukyanau
 */

public class ExceptionConstants {

	public static final String ERROR = "ERROR";

	public static final String BAD_REQUEST_DEFAULT_MESSAGE = "There are unaccepted parameters";

	private ExceptionConstants() {
		throw new IllegalStateException("Utility Class");
	}
}