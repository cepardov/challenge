package com.globallogic.products.application.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductRequest(
        @NotBlank
        @Size(min = 2, max = 80)
        String name,

        @NotNull
        @DecimalMin(value = "1.0")
        @Digits(integer = 10, fraction = 2)
        BigDecimal price,

        int initialStock
) {
}
