package com.yoti.sample.hoover.clean.common.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Class represents error response
 *
 * @author Artem Lukyanau
 */

@Data
@AllArgsConstructor
public class ErrorResponse<T> implements Serializable {
    private static final long serialVersionUID = 7702319210373510132L;

    private String status;
    private String code;
    private String message;
    private T data;
}
