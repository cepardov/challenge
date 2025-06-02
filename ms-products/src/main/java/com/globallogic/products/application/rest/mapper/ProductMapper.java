package com.globallogic.products.application.rest.mapper;

import com.globallogic.products.application.rest.dto.ProductRequest;
import com.globallogic.products.application.rest.dto.ProductResponse;
import com.globallogic.products.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("productRestMapper")
public class ProductMapper {

    public Product toDomain(ProductRequest productRequest) {
        log.debug("[toDomain] ProductRequest: {}", productRequest);
        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .build();
        log.debug("[toDomain] Product: {}", product);
        return product;
    }

    public ProductResponse toResponse(Product product) {
        log.debug("[toResponse] Product: {}", product);
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        log.debug("[toResponse] ProductResponse: {}", productResponse);
        return productResponse;
    }

}
