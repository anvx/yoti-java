package com.yoti.sample.hoover.clean.exception;

/**
 * WrongDirectionException is the exception for wrong input (when direction is incorrect)
 *
 * @author Artem Lukyanau
 */

public class WrongDirectionException extends RuntimeException {
    static final long serialVersionUID = -3234817190345756939L;

    /**
     * Constructs a new WrongDirectionException exception with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WrongDirectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new WrongDirectionException exception with the specified detail message and
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
    public WrongDirectionException(String message, Throwable cause) {
        super(message, cause);
    }
}