package com.globallogic.products.infrastructure;

import com.globallogic.products.domain.model.Product;
import java.util.List;

public interface ProductRepository {

    Product save(Product product);
    Product update(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void deleteById(Long id);

}
