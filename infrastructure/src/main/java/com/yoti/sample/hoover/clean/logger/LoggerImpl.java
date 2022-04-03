package com.yoti.sample.hoover.clean.logger;

import com.yoti.sample.hoover.clean.ports.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Logger proxy for domain model.
 *
 * @author Artem Lukyanau*/
@Slf4j
@Service
public class LoggerImpl implements Logger {
    /** For error logging */
    @Override
    public void logError(String message) {
        log.error(message);
    }

    /** For info logging */
    @Override
    public void logInfo(String message) {
        log.info(message);
    }

    /** For warning logging */
    @Override
    public void logWarning(String message) {
        log.warn(message);
    }
}
