package com.globallogic.products.infrastructure.datastore.mapper;

import com.globallogic.products.domain.model.Product;
import com.globallogic.products.infrastructure.datastore.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(Product product);
    Product toDomain(ProductEntity productEntity);

}
