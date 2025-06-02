package com.globallogic.products.application.usecase

import com.globallogic.products.domain.ProductService
import com.globallogic.products.domain.model.Product
import com.globallogic.products.shared.exception.ProductNotFoundException
import reactor.core.publisher.Mono
import spock.lang.Specification

class DeleteProductUseCaseSpec extends Specification {

    private DeleteProductUseCase delete
    private ProductService service

    def setup() {
        service = Mock()
        delete = new DeleteProductUseCase(
                service
        )
    }

    def "Test delete product"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        delete.execute(1).block()

        then:
        1 * service.findById(_) >> Mono.just(productSaved)
        1 * service.delete(_) >> Mono.empty()
        notThrown(Exception)
    }

    def "Test delete product not found"() {
        when:
        delete.execute(2).block()

        then:
        1 * service.findById(_) >> Mono.empty()
        0 * service.delete(_) >> Mono.empty()
        thrown(ProductNotFoundException)
    }

}
