package com.yoti.sample.hoover.clean.delivery.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A HooverRestRs is a response from hoover API.
 *
 * @author Artem Lukyanau
 */
@AllArgsConstructor
@Getter
@Setter
public class HooverRestRs implements Serializable {
    /** Last position of hoover */
    Integer[] coords;

    /** Count of collected patches */
    Long patches;
}
