package com.globallogic.products.application.rest.dto;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductResponse(
        Long id,
        String name,
        BigDecimal price
) {
}
