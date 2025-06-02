package com.globallogic.products.application.rest;

import com.globallogic.products.application.rest.dto.ProductRequest;
import com.globallogic.products.application.rest.dto.ProductResponse;
import com.globallogic.products.application.rest.mapper.ProductMapper;
import com.globallogic.products.application.usecase.CreateProductUseCase;
import com.globallogic.products.application.usecase.DeleteProductUseCase;
import com.globallogic.products.application.usecase.GetProductByIdUseCase;
import com.globallogic.products.application.usecase.UpdateProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductMapper productRestMapper;
    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @PostMapping
    public Mono<ResponseEntity<ProductResponse>> save(@RequestBody @Valid ProductRequest request) {
        log.debug("[save] ProductRequest: {}", request);
        return createProductUseCase.execute(productRestMapper.toDomain(request))
                .map(productRestMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> update(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        log.debug("[update] id: {}, request: {}", id, request);
        return updateProductUseCase.execute(id, productRestMapper.toDomain(request))
                .map(productRestMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> getById(@PathVariable Long id) {
        log.debug("[getById] id: {}", id);
        return getProductByIdUseCase.execute(id)
                .map(productRestMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        log.debug("[delete] id: {}", id);
        return deleteProductUseCase.execute(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
