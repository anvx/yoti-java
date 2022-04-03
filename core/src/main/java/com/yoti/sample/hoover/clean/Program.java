package com.yoti.sample.hoover.clean;

import com.yoti.sample.hoover.clean.enums.Directions;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Program contain main information about patches, directions and room size.
 *
 * @author Artem Lukyanau
 */

@Data
@AllArgsConstructor
public class Program {
    /** A Map contains count of patches by {@link Position} */
    private Map<Position, Integer> patchesMap;

    /** A directions for hoover moving */
    private List<Directions> directions;

    /** A room size presented by {@link Position} */
    private Position roomSize;
}
