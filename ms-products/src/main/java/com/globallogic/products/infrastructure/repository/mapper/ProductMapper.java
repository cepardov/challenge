package com.globallogic.products.infrastructure.repository.mapper;

import com.globallogic.products.domain.model.Product;
import com.globallogic.products.infrastructure.repository.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Mapper for product repository.
 */
@Slf4j
@Component("productRepositoryMapper")
public class ProductMapper {

    public ProductEntity toEntity(Product product) {
        log.debug("[toEntity] product: {}", product);
        ProductEntity productEntity = ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        log.debug("[toEntity] productEntity: {}", productEntity);
        return productEntity;
    }

    public Product toDomain(ProductEntity productEntity) {
        log.debug("[toDomain] productEntity: {}", productEntity);
        Product product = Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .build();
        log.debug("[toDomain] product: {}", product);
        return product;
    }

}
