package com.yoti.sample.hoover.clean.exception;

/**
 * HooverValidationException is the exception thrown while Hoover validation.
 *
 * @author Artem Lukyanau
 */

public class HooverValidationException extends RuntimeException {
    static final long serialVersionUID = -1114897190745766939L;

    /**
     * Constructs a new HooverValidationException exception with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public HooverValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new HooverValidationException exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public HooverValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
