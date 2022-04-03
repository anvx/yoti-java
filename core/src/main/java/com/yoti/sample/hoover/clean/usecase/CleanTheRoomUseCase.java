package com.yoti.sample.hoover.clean.usecase;

import com.yoti.sample.hoover.clean.Hoover;

/**
 * Main purpose of this use case is clean the room by Hoover.
 *
 * @author Artem Lukyanau
 */

public interface CleanTheRoomUseCase {

    /**
     * Entry point to the cleaning process
     *
     * @param hoover represent all main field of cleaning process
     */
    Hoover execute(Hoover hoover);

}
