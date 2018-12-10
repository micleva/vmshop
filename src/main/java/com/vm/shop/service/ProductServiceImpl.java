package com.vm.shop.service;

import com.vm.shop.model.Product;
import com.vm.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList;
    }

    @Override
    public Product save(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product update(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}
