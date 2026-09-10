package com.example.springboottest.exception;

import java.time.LocalDateTime;

public record CustomMessageError(
        int status,
        LocalDateTime timeStamp,
        String message,
        String description
) {
}