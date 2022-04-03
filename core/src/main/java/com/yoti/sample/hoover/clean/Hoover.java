package com.yoti.sample.hoover.clean;

import com.yoti.sample.hoover.clean.exception.HooverOutsideRoomException;
import com.yoti.sample.hoover.clean.exception.HooverValidationException;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Hoover represent domain object which contain all main information about cleaning process.
 *
 * @author Artem Lukyanau
 */

@Data
@Builder
public class Hoover implements Serializable {
    /** Count of collected patches */
    private long patchesCounter;

    /** Start position */
    private Position startPosition;

    /** Current position. Changes according to the directions */
    private Position currentPosition;

    /** Program contain information about patches position, room size and directions */
    private Program program;

    /** Invoke this method to validate Hoover(invariant) */
    public void validateItself() {
        validateRoomSize();
        validateStartPosition();
        validatePatchesPosition();
        validateDirections();
    }

    private void validateDirections() {
        if (program.getDirections().isEmpty()) {
            throw new HooverValidationException("Directions should contain at least one movement.");
        }
    }

    private void validateRoomSize() {
        Position roomSize = program.getRoomSize();
        boolean isRoomSizeCorrect = roomSize.getX() > 0 && roomSize.getY() > 0;
        if (!isRoomSizeCorrect) {
            throw new HooverValidationException("Room size should be positive.");
        }
    }

    private void validateStartPosition() {
        Position roomSize = program.getRoomSize();
        boolean isStartPositionOutOfRoom = startPosition.getX() >= roomSize.getX()
                || startPosition.getY() >= roomSize.getY()
                || startPosition.getX() < 0
                || startPosition.getY() < 0;
        if (isStartPositionOutOfRoom) {
            throw new HooverOutsideRoomException("Hoover starting position outside of Room.");
        }
    }

    private void validatePatchesPosition() {
        Position roomSize = program.getRoomSize();
        boolean isAllPatchesInsideRoom = program.getPatchesMap().keySet().stream()
                .allMatch(dust -> dust.getX() >= 0
                        && dust.getY() >= 0
                        && dust.getX() < roomSize.getX()
                        && dust.getY() < roomSize.getY());
        if (!isAllPatchesInsideRoom) {
            throw new HooverValidationException("Patches couldn't be outside the Room.");
        }
    }
}
