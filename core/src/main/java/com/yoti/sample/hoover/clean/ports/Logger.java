package com.yoti.sample.hoover.clean.ports;

/**
 * Wrapper for logger. Use it for logging event in Domain layer.
 *
 * @author Artem Lukyanau
 */

public interface Logger {

    void logError(String message);

    void logInfo(String message);

    void logWarning(String message);

}
