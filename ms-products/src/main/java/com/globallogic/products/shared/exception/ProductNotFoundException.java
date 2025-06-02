package com.globallogic.products.shared.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private final String errorCode;

    public ProductNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
