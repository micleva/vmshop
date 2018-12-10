package com.vm.shop.controller;

import com.vm.shop.model.Product;
import com.vm.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/api/product")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product p) {
        return ResponseEntity.ok(productService.save(p));
    }

    @GetMapping(path = "/api/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PutMapping(path = "/api/product")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product p) {
        return ResponseEntity.ok(productService.update(p));
    }

    @GetMapping(path = "/api/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "productId", required = true) Long productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/product/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable(name = "productId", required = true) Long productId) {
        productService.deleteById(productId);

        return ResponseEntity.noContent().build();
    }
}
