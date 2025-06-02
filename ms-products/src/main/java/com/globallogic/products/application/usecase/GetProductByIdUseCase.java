package com.globallogic.products.application.usecase;

import com.globallogic.products.domain.ProductService;
import com.globallogic.products.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetProductByIdUseCase {

    private final ProductService productService;

    @Transactional(readOnly = true)
    public Mono<Product> execute(Long id) {
        log.debug("[] id: {}", id);
        return productService.findById(id);
    }
}
