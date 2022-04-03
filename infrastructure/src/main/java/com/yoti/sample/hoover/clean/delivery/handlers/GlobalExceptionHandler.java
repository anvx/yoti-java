package com.yoti.sample.hoover.clean.delivery.handlers;

import com.yoti.sample.hoover.clean.common.constants.ExceptionConstants;
import com.yoti.sample.hoover.clean.common.exception.ConverterException;
import com.yoti.sample.hoover.clean.common.responses.ErrorResponse;
import com.yoti.sample.hoover.clean.common.responses.ValidationErrorResponse;
import com.yoti.sample.hoover.clean.common.responses.Violation;
import com.yoti.sample.hoover.clean.exception.HooverOutsideRoomException;
import com.yoti.sample.hoover.clean.exception.HooverValidationException;
import com.yoti.sample.hoover.clean.exception.WrongDirectionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * GlobalExceptionHandler responsible for exception handling.
 * {@linkplain  ResponseEntityExceptionHandler} - base class that handle most common exception
 *
 * @author Artem Lukyanau
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse<Object> response = new ErrorResponse<>(
                ExceptionConstants.ERROR,
                String.valueOf(status.value()),
                status.getReasonPhrase(),
                ExceptionConstants.BAD_REQUEST_DEFAULT_MESSAGE);
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ErrorResponse<Object> response = new ErrorResponse<>(
                ExceptionConstants.ERROR,
                String.valueOf(status.value()),
                status.getReasonPhrase(),
                error);
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, response, headers, status, request);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> onConstraintValidationException(
            ConstraintViolationException ex,
            WebRequest request) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse<Object> response = new ErrorResponse<>(
                ExceptionConstants.ERROR,
                String.valueOf(badRequest.value()),
                badRequest.getReasonPhrase(),
                error);
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), badRequest, request);
    }


    @ExceptionHandler(value = {
            ConverterException.class,
            HooverOutsideRoomException.class,
            HooverValidationException.class,
            WrongDirectionException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse<Object> response = new ErrorResponse<>(
                ExceptionConstants.ERROR,
                String.valueOf(badRequest.value()),
                badRequest.getReasonPhrase(),
                ex.getMessage());
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), badRequest, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> globalHandler(Exception ex, WebRequest request) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse<Object> response = new ErrorResponse<>(
                ExceptionConstants.ERROR,
                String.valueOf(internalServerError.value()),
                internalServerError.getReasonPhrase(),
                ex.getMessage());
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), internalServerError, request);
    }
}
