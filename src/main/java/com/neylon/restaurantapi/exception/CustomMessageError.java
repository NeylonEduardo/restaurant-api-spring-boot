package com.neylon.restaurantapi.exception;

import java.time.LocalDateTime;

public record CustomMessageError(
        int status,
        LocalDateTime timeStamp,
        String message,
        String description
) {
}