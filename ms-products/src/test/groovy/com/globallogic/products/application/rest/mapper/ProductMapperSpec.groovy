package com.globallogic.products.application.rest.mapper

import com.globallogic.products.application.rest.dto.ProductRequest
import com.globallogic.products.domain.model.Product
import spock.lang.Specification

class ProductMapperSpec extends Specification {

    private ProductMapper mapper

    def setup() {
        mapper = new ProductMapper()
    }

    def "Test toDomain"() {
        given:
        ProductRequest productRequest = ProductRequest.builder()
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .initialStock(1)
                .build()

        when:
        def product = mapper.toDomain(productRequest)

        then:
        notThrown(Exception)

        and:
        product != null
        product.id == null
        product.name == "name"
        product.price == 100.0
    }

    def "Test toResponse"() {
        given:
        Product product = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def productResponse = mapper.toResponse(product)

        then:
        notThrown(Exception)

        and:
        productResponse != null
        productResponse.id() == product.id
        productResponse.name() == product.name
        productResponse.price() == product.price
    }
}
