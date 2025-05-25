package com.globallogic.products.infrastructure.datastore;

import com.globallogic.products.domain.model.Product;
import com.globallogic.products.infrastructure.ProductRepository;
import com.globallogic.products.infrastructure.datastore.entity.ProductEntity;
import com.globallogic.products.infrastructure.datastore.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductMariaDbRepository implements ProductRepository {

    private final ProductJpaRepository repository;
    private final ProductMapper mapper;

    @Override
    public Product save(Product product) {
        log.debug("[save] Saving product: {}", product);
        ProductEntity productEntity = mapper.toEntity(product);
        ProductEntity savedProductEntity = repository.save(productEntity);
        return mapper.toDomain(savedProductEntity);
    }

    @Override
    public Product update(Product product) {
        log.debug("[update] Updating product: {}", product);
        ProductEntity productEntity = mapper.toEntity(product);
        ProductEntity updatedProductEntity = repository.save(productEntity);
        return mapper.toDomain(updatedProductEntity);
    }

    @Override
    public List<Product> findAll() {
        log.debug("[findAll] Finding all products");
        List<ProductEntity> productEntities = repository.findAll();
        return productEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Product findById(Long id) {
        log.debug("[findById] Finding product by id: {}", id);
        ProductEntity productEntity = repository.findById(id).orElse(null);
        if (productEntity != null) {
            return mapper.toDomain(productEntity);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("[deleteById] Deleting product by id: {}", id);
        repository.deleteById(id);
    }
}
