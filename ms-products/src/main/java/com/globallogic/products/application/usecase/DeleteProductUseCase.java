package com.globallogic.products.application.usecase;

import com.globallogic.products.domain.ProductService;
import com.globallogic.products.shared.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final ProductService productService;

    @Transactional(rollbackFor = {Exception.class})
    public Mono<Void> execute(Long id) {
        log.debug("[execute] id: {}", id);
        return productService.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found", "404")))
                .flatMap(productService::delete);
    }
}
