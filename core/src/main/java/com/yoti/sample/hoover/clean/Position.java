package com.yoti.sample.hoover.clean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Position represent cartesian coordinates.
 *
 * @author Artem Lukyanau
 */

@Data
@AllArgsConstructor
public class Position {
    /** coordinate x */
    private int x;

    /** coordinate y */
    private int y;
}
