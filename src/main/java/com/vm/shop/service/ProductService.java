package com.vm.shop.service;

import com.vm.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product save(Product p);

    Product update(Product p);

    Product findById(Long productId);

    void deleteById(Long productId);
}
