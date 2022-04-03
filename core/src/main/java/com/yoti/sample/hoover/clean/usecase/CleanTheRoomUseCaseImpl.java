package com.yoti.sample.hoover.clean.usecase;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.Position;
import com.yoti.sample.hoover.clean.enums.Directions;
import com.yoti.sample.hoover.clean.ports.HooverRepositoryService;
import com.yoti.sample.hoover.clean.ports.Logger;
import lombok.AllArgsConstructor;

/**
 * Implementation of {@linkplain CleanTheRoomUseCase}.
 *
 * @author Artem Lukyanau
 */

@AllArgsConstructor
public class CleanTheRoomUseCaseImpl implements CleanTheRoomUseCase {

    private final HooverRepositoryService hooverRepositoryService;
    private final Logger logger;

    /**
     * Main unit of work.
     *
     * Work flow is:
     * 1) collect patches at current position
     * 2) get next direction
     * 3) try to move
     *
     * @param hoover represent all main field of cleaning process
     */
    @Override
    public Hoover execute(Hoover hoover) {
        hoover.validateItself();

        collectPatches(hoover, hoover.getCurrentPosition());

        for (Directions direction : hoover.getProgram().getDirections()) {
            moveToNextPosition(hoover, getNewPosition(direction, hoover.getCurrentPosition()));
            collectPatches(hoover, hoover.getCurrentPosition());
        }

        hooverRepositoryService.saveHoover(hoover);

        return hoover;
    }

    private void moveToNextPosition(Hoover hoover, Position newPosition) {
        Position roomSize = hoover.getProgram().getRoomSize();
        boolean isNewPositionInTheRoom = newPosition.getX() < roomSize.getX()
                && newPosition.getY() < roomSize.getY()
                && newPosition.getX() >= 0
                && newPosition.getY() >= 0;
        if (isNewPositionInTheRoom) {
            setNewPositionToHoover(hoover, newPosition);
        } else {
            logger.logInfo("Next position out of room. Skids in place.");
        }
    }

    private void setNewPositionToHoover(Hoover hoover, Position newPosition) {
        hoover.setCurrentPosition(newPosition);
    }

    private Position getNewPosition(Directions way, Position currentPosition) {
        Position positionChanges = way.getPositionChanges();
        return new Position(
                currentPosition.getX() + positionChanges.getX(),
                currentPosition.getY() + positionChanges.getY()
        );
    }

    private void collectPatches(Hoover hoover, Position currentPosition) {
        Integer countOfCollectedPatches = hoover.getProgram().getPatchesMap().getOrDefault(currentPosition, 0);
        logger.logInfo(String.format("Removed %s patches. %s.", countOfCollectedPatches, currentPosition));
        hoover.getProgram().getPatchesMap().remove(currentPosition);
        hoover.setPatchesCounter(hoover.getPatchesCounter() + countOfCollectedPatches);
    }
}