package com.vm.shop.repositories;

import com.vm.shop.model.Order;
import com.vm.shop.model.Product;
import com.vm.shop.model.SoldProduct;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    @Before
    public void intiProducts() {
        Product p1 = buildProduct("SSD", new BigDecimal("120.52"));
        Product p2 = buildProduct("DDR4", new BigDecimal("180.31"));

        products = new ArrayList<>();
        products.add(productRepository.save(p1));
        products.add(productRepository.save(p2));
    }

    @After
    public void cleanProducts() {
        for (Product product : products) {
            productRepository.deleteById(product.getId());
        }
    }

    @Test
    @Ignore
    public void testSave() {
        //todo: fix this test
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        Order order = new Order();
        order.setRegistrationDate(now);
        order.setBuyerEmail("micle.valer@gmail.com");

        List<SoldProduct> soldProducts = products.stream().map(this::toSoldProduct).collect(Collectors.toList());
        order.setProducts(soldProducts);

        orderRepository.save(order);

        if (order.getId() != null) {
            orderRepository.deleteById(order.getId());
        }
    }

    private Product buildProduct(String name, BigDecimal price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        return product;
    }

    private SoldProduct toSoldProduct(Product product) {
        SoldProduct soldProduct = new SoldProduct();
        soldProduct.setProduct(product);
        soldProduct.setSoldPrice(product.getPrice());

        return soldProduct;
    }
}