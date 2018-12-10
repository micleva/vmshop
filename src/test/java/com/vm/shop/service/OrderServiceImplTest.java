package com.vm.shop.service;

import com.vm.shop.model.Order;
import com.vm.shop.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testFindById() throws Exception {
        Order order = new Order();
        order.setId(123L);

        when(orderRepository.findById(123L)).thenReturn(Optional.of(order));

        //DO THE ACTUAL CALL
        Order actualOrder = orderService.findById(123L);

        assertThat(actualOrder, is(sameInstance(order)));

        verify(orderRepository).findById(123L);
        verifyNoMoreInteractions(orderRepository);
    }


}