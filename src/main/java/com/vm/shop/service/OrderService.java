package com.vm.shop.service;

import com.vm.shop.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> getAll();

    Order findById(Long orderId);

}
