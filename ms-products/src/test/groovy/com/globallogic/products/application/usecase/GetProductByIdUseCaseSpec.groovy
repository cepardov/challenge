package com.globallogic.products.application.usecase

import com.globallogic.products.domain.ProductService
import com.globallogic.products.domain.model.Product
import reactor.core.publisher.Mono
import spock.lang.Specification

class GetProductByIdUseCaseSpec extends Specification {

    private GetProductByIdUseCase getProductById
    private ProductService service

    def setup() {
        service = Mock()
        getProductById = new GetProductByIdUseCase(
                service
        )
    }

    def "Test get product by id 1"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def res = getProductById.execute(1).block()

        then:
        1 * service.findById(_) >> Mono.just(productSaved)
        notThrown(Exception)

        and:
        res.id == 1
    }
}
