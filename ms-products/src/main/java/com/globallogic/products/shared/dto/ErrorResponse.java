package com.globallogic.products.shared.dto;

public record ErrorResponse(
        String errorCode,
        String message
) {
}
