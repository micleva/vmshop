package com.vm.shop.controller;

import com.vm.shop.model.Order;
import com.vm.shop.model.SoldProduct;
import com.vm.shop.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Test
    public void test_createNewOrder() throws Exception {

        Order order = new Order();

        Mockito.doAnswer(invocation -> {
            Order actualOrder = invocation.getArgument(0);
            actualOrder.setId(123L);
            return actualOrder;
        }).when(orderService).save(order);


        ResponseEntity<Order> response = orderController.placeOrder(order);

        Assert.assertEquals(200, response.getStatusCode().value());

        Order placedOrder = response.getBody();
        assertThat(placedOrder.getId(), is(123L));
        assertThat(placedOrder.getRegistrationDate(), is(notNullValue()));

        verify(orderService).save(order);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void test_calculateTotalPrice() throws Exception {

        SoldProduct sp1 = new SoldProduct();
        sp1.setSoldPrice(new BigDecimal("12.554"));

        SoldProduct sp2 = new SoldProduct();
        sp2.setSoldPrice(new BigDecimal("15.552"));

        Order order = new Order();
        order.setRegistrationDate(LocalDateTime.now());
        order.setProducts(Arrays.asList(sp1, sp2));

        when(orderService.findById(111L)).thenReturn(order);

        //DO THE ACTUAL CALL
        ResponseEntity<BigDecimal> response = orderController.calculateTotalPrice(111L);
        BigDecimal result = response.getBody();


        assertThat(result, is(new BigDecimal("28.11")));

        verify(orderService).findById(111L);
        verifyNoMoreInteractions(orderService);
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }
}