package com.globallogic.products.infrastructure.repository.mapper

import com.globallogic.products.domain.model.Product
import com.globallogic.products.infrastructure.repository.entity.ProductEntity
import spock.lang.Specification

class ProductMapperSpec extends Specification {

    private ProductMapper mapper

    def setup() {
        mapper = new ProductMapper()
    }

    def "Test toDomain"() {
        given:
        ProductEntity product = ProductEntity.builder()
                .build()

        when:
        def res = mapper.toDomain(product)

        then:
        res != null
        notThrown(Exception)
    }

    def "Test"() {
        given:
        Product product = Product.builder()
                .build()

        when:
        def res = mapper.toEntity(product)

        then:
        res != null
        notThrown(Exception)
    }
}
