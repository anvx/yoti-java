package com.yoti.sample.hoover.clean.delivery.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * A TwoDimensionArrayValidator validate two dimension array
 *
 * @see {@link TwoDimensionArray}
 * @author Artem Lukyanau
 */

public class TwoDimensionArrayValidator implements ConstraintValidator<TwoDimensionArray, Integer[][]> {

    @Override
    public boolean isValid(Integer[][] patchesCoordinates, ConstraintValidatorContext constraintValidatorContext) {
        if (patchesCoordinates == null) {
            return false;
        }
        return Arrays.stream(patchesCoordinates).allMatch(array -> Arrays.stream(array).count() == 2);
    }
}