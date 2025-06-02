package com.globallogic.products.application.usecase;

import com.globallogic.products.domain.ProductService;
import com.globallogic.products.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductService productService;

    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    public Mono<Product> execute(Product product) {
        return productService.save(product);
    }
}
