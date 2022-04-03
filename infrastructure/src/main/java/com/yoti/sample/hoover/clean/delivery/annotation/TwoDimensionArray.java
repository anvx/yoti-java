package com.yoti.sample.hoover.clean.delivery.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for validating input two dimension array
 *
 * @author Artem Lukyanau
 */

@Documented
@Constraint(validatedBy = TwoDimensionArrayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TwoDimensionArray {
    String message() default "Invalid patches coordinates.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
