package com.vm.shop.controller;

import com.vm.shop.model.Order;
import com.vm.shop.model.SoldProduct;
import com.vm.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/api/order")
    public ResponseEntity<Order> placeOrder(@RequestBody @Valid Order order) {
        order.setRegistrationDate(LocalDateTime.now());
        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping(path = "/api/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping(path = "/api/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "orderId", required = true) Long orderId) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/api/order/{orderId}/calculate")
    public ResponseEntity<BigDecimal> calculateTotalPrice(@PathVariable(name = "orderId", required = true) Long orderId) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            BigDecimal totalPrice = new BigDecimal(0);
            for (SoldProduct soldProduct : order.getProducts()) {
                totalPrice = totalPrice.add(soldProduct.getSoldPrice());
            }
            totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            return ResponseEntity.ok(totalPrice);
        }
        return ResponseEntity.notFound().build();
    }
}
