package com.test.organization.controller;

import com.test.organization.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .code("BAD_REQUEST")
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .code("INTERNAL_SERVER_ERROR")
                .message("Unknown error")
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ErrorResponse {
        private String code;
        private String message;
    }
}