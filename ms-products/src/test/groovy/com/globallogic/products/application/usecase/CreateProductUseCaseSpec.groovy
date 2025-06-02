package com.globallogic.products.application.usecase

import com.globallogic.products.domain.ProductService
import com.globallogic.products.domain.model.Product
import reactor.core.publisher.Mono
import spock.lang.Specification

class CreateProductUseCaseSpec extends Specification {

    private CreateProductUseCase create
    private ProductService service

    def setup() {
        service = Mock()
        create = new CreateProductUseCase(
                service
        )
    }

    def "Test save product"() {
        given:
        Product product = Product.builder()
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def res = create.execute(product).block()

        then:
        1 * service.save(_) >> Mono.just(productSaved)
        notThrown(Exception)

        and:
        res.id == 1
        res.name == "name"
        res.price == BigDecimal.valueOf(100.0)
    }
}
