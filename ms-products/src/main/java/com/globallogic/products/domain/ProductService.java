package com.globallogic.products.domain;

import com.globallogic.products.domain.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> save(Product product);

    Mono<Product> update(Product product);

    Mono<Product> findById(Long id);

    Mono<Void> delete(Product product);

}
