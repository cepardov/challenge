package com.globallogic.products.application.usecase;

import com.globallogic.products.domain.ProductService;
import com.globallogic.products.domain.model.Product;
import com.globallogic.products.shared.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateProductUseCase {

    private final ProductService productService;

    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    public Mono<Product> execute(Long id, Product product) {
        log.debug("[execute] id: {}, product: {}", id, product);
        //TODO auditoría
        return productService.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found", "404")))
                .flatMap(productFound -> {
                    product.setId(productFound.getId());
                    return productService.update(product);
                });
    }
}
