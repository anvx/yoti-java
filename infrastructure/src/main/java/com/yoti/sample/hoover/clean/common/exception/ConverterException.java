package com.yoti.sample.hoover.clean.common.exception;

/**
 * An ConverterException is thrown when an application tries to convert from REST presentation to domain
 *
 * @author Artem Lukyanau
 */

public class ConverterException extends RuntimeException {
    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
