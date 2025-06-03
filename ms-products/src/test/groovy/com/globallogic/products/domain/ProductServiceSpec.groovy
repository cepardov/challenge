package com.globallogic.products.domain

import com.globallogic.products.domain.model.Product
import com.globallogic.products.domain.service.ProductServiceImpl
import com.globallogic.products.infrastructure.ProductRepository
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    private ProductService service
    private ProductRepository repository

    def setup() {
        repository = Mock()
        service = new ProductServiceImpl(
                repository
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

        when: "call save method with product"
        def res = service.save(product).block()

        then: "Not thrown any exception and product id is 1"
        res.id == 1
        notThrown(Exception)

        and: "1 interaction with save in repository"
        1 * repository.save(_) >> productSaved
    }

    def "Test update product 1"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when: "call update method with product"
        def res = service.update(productSaved).block()

        then: "Not thrown any exception and product id is 1"
        res.id == 1
        notThrown(Exception)

        and: "1 interaction with save in repository"
        1 * repository.update(_) >> Optional.of(productSaved)
    }

    def "Test update unavailable product 2"() {
        given:
        Product productSaved = Product.builder()
                .id(2)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when: "Call update method with product"
        def res = service.update(productSaved).block()

        then: "Not thrown any exception and product is null"
        res == null
        notThrown(Exception)

        and: "1 interaction with repository and return empty"
        1 * repository.update(_) >> Optional.empty()
    }

    def "Test find product by id 1"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when: "call find by ad method with id 1"
        def res = service.findById(1).block()

        then: "Not thrown any exception and return product 1"
        res != null
        res.id == 1
        notThrown(Exception)

        and: "1 interaction whit repository and return product 1"
        1 * repository.findById(_) >> Optional.of(productSaved)
    }

    def "Test delete product 1"() {
        given:
        Product productSaved = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        service.delete(productSaved).block()

        then:
        notThrown(Exception)

        and:
        1 * repository.delete(_) >> {}
    }


}
