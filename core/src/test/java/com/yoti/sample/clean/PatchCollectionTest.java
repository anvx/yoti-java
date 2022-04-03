package com.yoti.sample.clean;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.Position;
import com.yoti.sample.hoover.clean.Program;
import com.yoti.sample.hoover.clean.enums.Directions;
import com.yoti.sample.hoover.clean.ports.HooverRepositoryService;
import com.yoti.sample.hoover.clean.ports.Logger;
import com.yoti.sample.hoover.clean.usecase.CleanTheRoomUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yoti.sample.hoover.clean.enums.Directions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatchCollectionTest {

    @Test
    public void hooverShouldCollectPatchAtStartingPointIfPatchExist() {
        Position startPosition = new Position(1, 1);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(1, 1), 1);

        List<Directions> directions = List.of(N);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();
        HooverRepositoryService repositoryService = Mockito.mock(HooverRepositoryService.class);
        Logger logger = Mockito.mock(Logger.class);
        CleanTheRoomUseCaseImpl cleanUseCase = new CleanTheRoomUseCaseImpl(repositoryService, logger);
        cleanUseCase.execute(hoover);

        assertEquals(new Position(1, 2), hoover.getCurrentPosition());
        assertEquals(1, hoover.getPatchesCounter());
    }

    @Test
    public void hooverShouldCollectPatchAllPatchesOnTheWay() {
        Position startPosition = new Position(1, 2);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(1, 0), 1);
        patchesMap.put(new Position(2, 2), 1);
        patchesMap.put(new Position(2, 3), 1);

        List<Directions> directions = List.of(N, N, E, S, E, E, S, W, N, W, W);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();

        HooverRepositoryService repositoryService = Mockito.mock(HooverRepositoryService.class);
        Logger logger = Mockito.mock(Logger.class);
        CleanTheRoomUseCaseImpl cleanUseCase = new CleanTheRoomUseCaseImpl(repositoryService, logger);
        cleanUseCase.execute(hoover);

        assertEquals(new Position(1, 3), hoover.getCurrentPosition());
        assertEquals(1, hoover.getPatchesCounter());
    }

    @Test
    public void hooverShouldSkidWhenNextPositionOutOfRoom() {
        Position startPosition = new Position(0, 0);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(1, 0), 1);
        patchesMap.put(new Position(2, 2), 1);
        patchesMap.put(new Position(2, 3), 1);

        List<Directions> directions = List.of(E, E, E, E, E, E, N, N, N, N, N, N);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();

        HooverRepositoryService repositoryService = Mockito.mock(HooverRepositoryService.class);
        Logger logger = Mockito.mock(Logger.class);
        CleanTheRoomUseCaseImpl cleanUseCase = new CleanTheRoomUseCaseImpl(repositoryService, logger);
        cleanUseCase.execute(hoover);

        assertEquals(new Position(4, 4), hoover.getCurrentPosition());
        assertEquals(1, hoover.getPatchesCounter());
    }

    @Test
    public void hooverShouldCollectAllPatchesIfMoreThanOnePresent() {
        Position startPosition = new Position(1, 0);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(1, 0), 2);
        patchesMap.put(new Position(2, 2), 2);
        patchesMap.put(new Position(2, 3), 1);

        List<Directions> directions = List.of(N, N, E);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();

        HooverRepositoryService repositoryService = Mockito.mock(HooverRepositoryService.class);
        Logger logger = Mockito.mock(Logger.class);
        CleanTheRoomUseCaseImpl cleanUseCase = new CleanTheRoomUseCaseImpl(repositoryService, logger);
        cleanUseCase.execute(hoover);

        assertEquals(new Position(2, 2), hoover.getCurrentPosition());
        assertEquals(4, hoover.getPatchesCounter());
    }

    @Test
    public void hooverShouldNotCollectPatchesTwice() {
        Position startPosition = new Position(1, 0);

        Map<Position, Integer> patchesMap = new HashMap<>();
        patchesMap.put(new Position(1, 0), 2);
        patchesMap.put(new Position(2, 2), 2);
        patchesMap.put(new Position(2, 3), 1);

        List<Directions> directions = List.of(N, N, E, E, W, E, W);

        Position roomSize = new Position(5, 5);

        Program program = new Program(patchesMap, directions, roomSize);

        Hoover hoover = Hoover.builder()
                .startPosition(startPosition)
                .currentPosition(startPosition)
                .program(program)
                .build();

        HooverRepositoryService repositoryService = Mockito.mock(HooverRepositoryService.class);
        Logger logger = Mockito.mock(Logger.class);
        CleanTheRoomUseCaseImpl cleanUseCase = new CleanTheRoomUseCaseImpl(repositoryService, logger);
        cleanUseCase.execute(hoover);

        assertEquals(new Position(2, 2), hoover.getCurrentPosition());
        assertEquals(4, hoover.getPatchesCounter());
    }
}