package com.yoti.sample.hoover.clean.delivery.converters;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.Position;
import com.yoti.sample.hoover.clean.Program;
import com.yoti.sample.hoover.clean.common.RestConverter;
import com.yoti.sample.hoover.clean.common.exception.ConverterException;
import com.yoti.sample.hoover.clean.delivery.rest.HooverRestRq;
import com.yoti.sample.hoover.clean.enums.Directions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link RestConverter}
 *
 * @author Artem Lukyanau
 */
@Component
public class HooverRestRqConverter implements RestConverter<HooverRestRq, Hoover> {

    @Override
    public Hoover mapToEntity(final HooverRestRq hooverRest) {

        Position currentPosition = getStartPosition(hooverRest);
        Program program = getProgram(hooverRest);

        return Hoover.builder()
                .patchesCounter(0)
                .startPosition(currentPosition)
                .currentPosition(currentPosition)
                .program(program)
                .build();
    }

    @Override
    public HooverRestRq mapToRest(final Hoover entity) {
        Integer[] roomSize = getRoomSize(entity);
        Integer[] coords = getCoords(entity);
        Integer[][] patches = getPatches(entity);
        String directions = getInstructions(entity);

        return new HooverRestRq(roomSize, coords, patches, directions);
    }

    private String getInstructions(Hoover entity) {
        return entity.getProgram().getDirections().stream().map(Enum::name).collect(Collectors.joining());
    }

    private Integer[][] getPatches(Hoover entity) {
        Map<Position, Integer> patchMap = entity.getProgram().getPatchesMap();
        int countOfPatches = patchMap.values().stream().reduce(Integer::sum)
                .orElseThrow(() -> new ConverterException("Convert error."));
        List<Integer[]> patchesCoordinates = new ArrayList<>(countOfPatches);
        patchMap.forEach((key, value) -> {
            int count = value;
            for (int i = 0; i < count; i++) {
                patchesCoordinates.add(new Integer[]{key.getX(), key.getY()});
            }
        });
        return patchesCoordinates.toArray(new Integer[0][]);
    }

    private Integer[] getCoords(Hoover entity) {
        Position startPosition = entity.getStartPosition();
        return new Integer[]{startPosition.getX(), startPosition.getY()};
    }

    private Integer[] getRoomSize(Hoover entity) {
        Position roomSizeByPosition = entity.getProgram().getRoomSize();
        return new Integer[]{roomSizeByPosition.getX(), roomSizeByPosition.getY()};
    }

    private Program getProgram(HooverRestRq hooverRest) {
        Map<Position, Integer> patchesMap = Arrays.stream(hooverRest.getPatches())
                .collect(Collectors.toMap(array -> new Position(array[0], array[1]), key -> 1, Integer::sum));

        List<Directions> directions = Stream.of(hooverRest.getInstructions().split(""))
                .map(Directions::getDirectionsByName)
                .collect(Collectors.toList());

        Position roomSize = new Position(hooverRest.getRoomSize()[0], hooverRest.getRoomSize()[1]);

        return new Program(patchesMap, directions, roomSize);
    }

    private Position getStartPosition(HooverRestRq hooverRest) {
        return new Position(hooverRest.getCoords()[0], hooverRest.getCoords()[1]);
    }
}