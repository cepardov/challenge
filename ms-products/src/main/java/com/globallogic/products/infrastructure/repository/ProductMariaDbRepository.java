package com.globallogic.products.infrastructure.repository;

import com.globallogic.products.domain.model.Product;
import com.globallogic.products.infrastructure.ProductRepository;
import com.globallogic.products.infrastructure.repository.entity.ProductEntity;
import com.globallogic.products.infrastructure.repository.mapper.ProductMapper;
import java.util.List;
import java.util.Optional;
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
    public Optional<Product> update(Product product) {
        log.debug("[update] Updating product: {}", product);
        ProductEntity productEntity = mapper.toEntity(product);
        Optional<ProductEntity> savedProductEntityOptional = repository.findById(productEntity.getId());
        if (savedProductEntityOptional.isEmpty()) {
            log.debug("[update] Product not found: {}", productEntity.getId());
            return Optional.empty();
        }
        ProductEntity updatedProductEntity = repository.save(productEntity);
        return Optional.of(mapper.toDomain(updatedProductEntity));
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
    public Optional<Product> findById(Long id) {
        log.debug("[findById] Finding product by id: {}", id);
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(Product product) {
        log.debug("[delete] Delete product: {}", product);
        ProductEntity productToDelete = mapper.toEntity(product);
        repository.delete(productToDelete);
    }
}
