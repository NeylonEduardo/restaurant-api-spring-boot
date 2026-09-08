package com.example.springboottest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<CustomMessageError> handleNotFound(
            RestaurantNotFoundException exception,
            WebRequest request
    ) {
        CustomMessageError error = new CustomMessageError
                (
                        HttpStatus.NOT_FOUND.value(),
                        LocalDateTime.now(),
                        exception.getMessage(),
                        request.getDescription(false)
                );
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}