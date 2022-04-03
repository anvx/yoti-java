package com.yoti.sample.hoover.clean.common.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class represents violation during validation
 *
 * @author Artem Lukyanau
 */

@Data
@AllArgsConstructor
public class Violation {
    /** Field name where error is occurred */
    private final String fieldName;

    /** Detailed message */
    private final String message;
}
