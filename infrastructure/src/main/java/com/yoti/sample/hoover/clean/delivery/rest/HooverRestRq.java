package com.yoti.sample.hoover.clean.delivery.rest;

import com.yoti.sample.hoover.clean.delivery.annotation.TwoDimensionArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A HooverRestRq is a request for hoover API.
 * Validated by hibernate-validator.
 *
 * @author Artem Lukyanau
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HooverRestRq implements Serializable {

    /** Room size */
    @NotNull
    @Size(min = 2, max = 2)
    private Integer[] roomSize;

    /** Initial coordinates. Provided as [x, y] */
    @NotNull
    @Size(min = 2, max = 2)
    private Integer[] coords;

    /** Patches location. Provided as [[x, y]] */
    @NotNull
    @TwoDimensionArray
    private Integer[][] patches;

    /** Instructions for hoover. Presents as N, E, S, W */
    @NotBlank
    private String instructions;
}
