package com.globallogic.products.infrastructure;

import com.globallogic.products.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> update(Product product);
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void delete(Product product);

}
