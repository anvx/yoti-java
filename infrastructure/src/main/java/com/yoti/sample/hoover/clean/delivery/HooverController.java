package com.yoti.sample.hoover.clean.delivery;

import com.yoti.sample.hoover.clean.delivery.rest.HooverRestRq;
import com.yoti.sample.hoover.clean.delivery.rest.HooverRestRs;

/**
 * A HooverController is an API for hoover
 *
 * @author Artem Lukyanau
 */
public interface HooverController {

    /**
     * Main entry point for cleaning by hoover.
     *
     * @param hooverRestRq request to API
     */
    HooverRestRs cleanTheRoom(HooverRestRq hooverRestRq);

}
