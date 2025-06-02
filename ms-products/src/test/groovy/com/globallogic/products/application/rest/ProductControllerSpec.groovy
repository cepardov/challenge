package com.globallogic.products.application.rest

import com.globallogic.products.application.rest.dto.ProductRequest
import com.globallogic.products.application.rest.mapper.ProductMapper
import com.globallogic.products.application.usecase.CreateProductUseCase
import com.globallogic.products.application.usecase.DeleteProductUseCase
import com.globallogic.products.application.usecase.GetProductByIdUseCase
import com.globallogic.products.application.usecase.UpdateProductUseCase
import com.globallogic.products.domain.model.Product
import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono
import spock.lang.Specification

class ProductControllerSpec extends Specification {

    private ProductController controller
    private ProductMapper mapper
    private CreateProductUseCase create
    private DeleteProductUseCase delete
    private GetProductByIdUseCase getById
    private UpdateProductUseCase update

    def setup() {
        mapper = new ProductMapper()
        create = Mock(CreateProductUseCase)
        delete = Mock(DeleteProductUseCase)
        getById = Mock(GetProductByIdUseCase)
        update = Mock(UpdateProductUseCase)
        controller = new ProductController(
                mapper,
                create,
                getById,
                update,
                delete
        )
    }

    def "Save"() {
        given:
        ProductRequest request = ProductRequest.builder()
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def res = controller.save(request).block()

        then:
        1 * create.execute(_) >> Mono.just(mapper.toDomain(request))
        notThrown(Exception)

        and:
        res.statusCode == HttpStatus.OK
        res.body != null
        res.body.name() == request.name()
        res.body.price() == request.price()
    }

    def "Delete"() {
        when:
        def res = controller.delete(1).block()

        then:
        1 * delete.execute(_) >> Mono.empty()
        notThrown(Exception)

        and:
        res.statusCode == HttpStatus.NO_CONTENT
    }

    def "Get by id"() {
        given:
        Product product = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def res = controller.getById(1).block()

        then:
        1 * getById.execute(_) >> Mono.just(product)
        notThrown(Exception)

        and:
        res.statusCode == HttpStatus.OK
        res != null
        res.body != null
        res.body.id() == 1
    }

    def "Update"() {
        given:
        ProductRequest request = ProductRequest.builder()
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .initialStock(1)
                .build()

        Product product = Product.builder()
                .id(1)
                .name("name")
                .price(BigDecimal.valueOf(100.0))
                .build()

        when:
        def res = controller.update(1, request).block()

        then:
        1 * update.execute(_, _) >> Mono.just(product)
        notThrown(Exception)

        and:
        res.statusCode == HttpStatus.OK
        res.body != null
        res.body.id() == 1
    }
}
