package org.example.online_sports.exceptions;

import org.example.online_sports.payload.ApiResponse;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
