package org.example.online_sports.exceptions;

import org.example.online_sports.payload.ApiResponse;

public class JWTException extends RuntimeException {
    public JWTException(String message) {
        super(message);
    }
}
