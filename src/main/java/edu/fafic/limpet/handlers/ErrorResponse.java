package edu.fafic.limpet.handlers;

import java.time.LocalDateTime;

public record ErrorResponse(String message, String timestamp) {

    public static ErrorResponse of(Exception ex) {
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now().toString());
    }
}
