package com.globallogic.products.application.usecase

import com.globallogic.products.domain.ProductService
import com.globallogic.products.domain.model.Product
import com.globallogic.products.shared.exception.ProductNotFoundException
import reactor.core.publisher.Mono
import spock.lang.Specification

class UpdateProductUseCaseSpec extends Specification {

    private UpdateProductUseCase update
    private ProductService service

    def setup() {
        service = Mock()
        update = new UpdateProductUseCase(
                service
        )
    }

    def "Test update product 1"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def res = update.execute(1, productSaved).block()

        then:
        1 * service.findById(_) >> Mono.just(productSaved)
        1 * service.update(_) >> Mono.just(productSaved)
        notThrown(Exception)

        and:
        res.id == 1
    }

    def "Test update product not found"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        update.execute(1, productSaved).block()

        then:
        1 * service.findById(_) >> Mono.empty()
        0 * service.update(_)
        thrown(ProductNotFoundException)
    }
}
