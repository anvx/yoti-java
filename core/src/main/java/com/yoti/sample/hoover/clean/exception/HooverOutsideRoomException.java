package com.yoti.sample.hoover.clean.exception;

/**
 * HooverOutsideRoomException is the exception for wrong input (when start position out of the Room)
 *
 * @author Artem Lukyanau
 */

public class HooverOutsideRoomException extends RuntimeException {
    static final long serialVersionUID = -1234897190945766939L;

    /**
     * Constructs a new HooverOutsideRoomException exception with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public HooverOutsideRoomException(String message) {
        super(message);
    }

    /**
     * Constructs a new HooverOutsideRoomException exception with the specified detail message and
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
    public HooverOutsideRoomException(String message, Throwable cause) {
        super(message, cause);
    }
}
