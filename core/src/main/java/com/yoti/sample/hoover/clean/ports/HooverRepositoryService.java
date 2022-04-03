package com.yoti.sample.hoover.clean.ports;

import com.yoti.sample.hoover.clean.Hoover;

/**
 * Repository service for Hoover.
 *
 * @author Artem Lukyanau
 */

public interface HooverRepositoryService {

    void saveHoover(Hoover hoover);

}
