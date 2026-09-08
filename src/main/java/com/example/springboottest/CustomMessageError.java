package com.example.springboottest;

import java.time.LocalDateTime;

public record CustomMessageError(
        int status,
        LocalDateTime timeStamp,
        String message,
        String description
) {
}