package com.yoti.sample.hoover.clean.enums;

import com.yoti.sample.hoover.clean.Position;
import com.yoti.sample.hoover.clean.exception.WrongDirectionException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Directions for hoover moving. Only four ways (rectangular room).
 *
 * @author Artem Lukyanau
 */

public enum Directions {
    /** North */
    N(new Position(0, 1)),

    /** East */
    E(new Position(1, 0)),

    /** South */
    S(new Position(0, -1)),

    /** West */
    W(new Position(-1, 0));

    private static final Map<String, Directions> directionsByName;

    static {
        directionsByName = Arrays.stream(Directions.values())
                .collect(Collectors.toMap(Enum::name, Function.identity()));
    }

    /** Represents (x, y). Show how x or y should change */
    @Getter
    private final Position positionChanges;


    Directions(Position positionChanges) {
        this.positionChanges = positionChanges;
    }

    public static Directions getDirectionsByName(String direction) {
        return Optional.ofNullable(directionsByName.get(direction))
                .orElseThrow(() -> new WrongDirectionException(String.format("Wrong direction - '%s'.", direction)));
    }
}