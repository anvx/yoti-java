package com.yoti.sample.clean;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.Position;
import com.yoti.sample.hoover.clean.Program;
import com.yoti.sample.hoover.clean.enums.Directions;
import com.yoti.sample.hoover.clean.exception.HooverOutsideRoomException;
import com.yoti.sample.hoover.clean.exception.HooverValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.yoti.sample.hoover.clean.enums.Directions.*;

public class HooverValidationTest {

    //Assume that room size is 5x5
    public static Stream<Arguments> provideStartingPoint() {
        return Stream.of(
                Arguments.of(new Position(5, 5)),
                Arguments.of(new Position(-1, 1)),
                Arguments.of(new Position(1, -1)),
                Arguments.of(new Position(-1, -1)),
                Arguments.of(new Position(0, -1)),
                Arguments.of(new Position(-1, 0))
        );
    }

    //Assume that room size is 5x5
    public static Stream<Arguments> providePatchesLocation() {
        return Stream.of(
                Arguments.of(Map.of(new Position(5, 5), 1)),
                Arguments.of(Map.of(new Position(-1, 1), 1)),
                Arguments.of(Map.of(new Position(1, -1), 1)),
                Arguments.of(Map.of(new Position(-1, -1), 1)),
                Arguments.of(Map.of(new Position(0, -1), 1)),
                Arguments.of(Map.of(new Position(-1, 0), 1))
        );
    }

    @ParameterizedTest
    @MethodSource("provideStartingPoint")
    public void whenStartingPointIncorrectShouldThrowEx(Position startPosition) {
        Assertions.assertThrows(HooverOutsideRoomException.class,
                () -> createHooverWithPosition(startPosition).validateItself());
    }

    private Hoover createHooverWithPosition(Position startPosition) {
        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(4, 1), 1);
        patchesMap.put(new Position(5, 1), 1);

        List<Directions> directions = List.of(E, W, S, N);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        return Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();
    }

    @ParameterizedTest
    @MethodSource("providePatchesLocation")
    public void whenPatchesLocationIncorrectShouldThrowEx(Map<Position, Integer> patchesMap) {
        Assertions.assertThrows(HooverValidationException.class,
                () -> createHooverWithPatchesMap(patchesMap).validateItself());
    }

    private Hoover createHooverWithPatchesMap(Map<Position, Integer> patchesMap) {
        Position startPosition = new Position(1, 1);

        List<Directions> directions = List.of(E, W, S, N);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        return Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();
    }

    @Test
    public void whenRoomSizeIncorrectShouldThrowEx() {
        Position startPosition = new Position(1, 1);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(4, 1), 1);
        patchesMap.put(new Position(5, 1), 1);

        List<Directions> directions = List.of(E, W, S, N);

        Position roomSize = new Position(-5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();

        Assertions.assertThrows(HooverValidationException.class, hoover::validateItself);
    }

    @Test
    public void whenDirectionsEmptyShouldThrowEx() {
        Position startPosition = new Position(1, 1);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(4, 1), 1);
        patchesMap.put(new Position(5, 1), 1);

        List<Directions> directions = List.of();

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();

        Assertions.assertThrows(HooverValidationException.class, hoover::validateItself);
    }
}