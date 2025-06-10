package com.globallogic.products.domain.service;

import com.globallogic.products.domain.ProductService;
import com.globallogic.products.domain.model.Product;
import com.globallogic.products.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> save(Product product) {
        log.debug("[save] Product: {}", product);
        return Mono.fromCallable(() -> productRepository.save(product))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Product> update(Product product) {
        log.debug("[update] Product: {}", product);
        return Mono.fromCallable(() -> productRepository.update(product))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty);
    }

    @Override
    public Mono<Product> findById(Long id) {
        log.debug("[findById] Id: {}", id);
        return Mono.fromCallable(() -> productRepository.findById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty);
    }

    @Override
    public Mono<Void> delete(Product product) {
        log.debug("[deleteCaCa] Product: {}", product);
        return Mono.fromRunnable(() -> productRepository.delete(product))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
