package com.vm.shop.repositories;

import com.vm.shop.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface OrderRepository extends CrudRepository<Order, Long> {
}
