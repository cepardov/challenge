package com.globallogic.products.infrastructure

import com.globallogic.products.domain.model.Product
import com.globallogic.products.infrastructure.repository.ProductJpaRepository
import com.globallogic.products.infrastructure.repository.ProductMariaDbRepository
import com.globallogic.products.infrastructure.repository.entity.ProductEntity
import com.globallogic.products.infrastructure.repository.mapper.ProductMapper
import spock.lang.Specification

class ProductRepositorySpec extends Specification {

    private ProductRepository repository
    private ProductJpaRepository jpaRepository
    private ProductMapper mapper

    def setup() {
        jpaRepository = Mock()
        mapper = new ProductMapper()
        repository = new ProductMariaDbRepository(
                jpaRepository,
                mapper
        )
    }

    def "Test save product"() {
        given:
        Product product = Product.builder()
                .build()

        when:
        def res = repository.save(product)

        then:
        1 * jpaRepository.save(_) >> ProductEntity.builder().build()
        notThrown(Exception)

        and:
        res == product
    }

    def "Test update product"() {
        given:
        Product product = Product.builder()
                .build()

        ProductEntity productEntity = ProductEntity.builder()
                .build()

        when:
        def res = repository.update(product)

        then:
        1 * jpaRepository.findById(_) >> Optional.of(productEntity)
        1 * jpaRepository.save(_) >> productEntity
        notThrown(Exception)

        and:
        res.get() == product
    }

    def "Test update product not exist"() {
        given:
        Product product = Product.builder()
                .build()

        when:
        def res = repository.update(product)

        then:
        1 * jpaRepository.findById(_) >> Optional.empty()
        0 * jpaRepository.save(_)
        notThrown(Exception)

        and:
        res.isEmpty()
    }

    def "Test find all products"() {
        given:
        def list = (1..5).stream()
                .map { ProductEntity.builder().id(it).build() }
                .toList()

        when:
        def res = repository.findAll()

        then:
        1 * jpaRepository.findAll() >> list
        notThrown(Exception)

        and:
        res.size() == 5
    }

    def "Test find product by id"() {
        given:
        ProductEntity productEntity = ProductEntity.builder()
                .id(1)
                .build()

        when:
        def res = repository.findById(1)

        then:
        1 * jpaRepository.findById(_) >> Optional.of(productEntity)
        notThrown(Exception)
    }

    def "Test delete product"() {
        given:
        Product product = Product.builder()
                .id(1)
                .build()

        when:
        repository.delete(product)

        then:
        1 * jpaRepository.delete(_) >> {}
        notThrown(Exception)
    }
}
