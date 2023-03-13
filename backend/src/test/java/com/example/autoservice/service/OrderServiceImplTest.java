package com.example.autoservice.service;

import com.example.autoservice.model.Car;
import com.example.autoservice.model.Commodity;
import com.example.autoservice.model.Favor;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.repository.OrderRepository;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.List;
import com.example.autoservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    void updateStatus_Ok() {
        Long id = 1L;
        updateStatusTest(id);
        Order.Status status = Order.Status.COMPLETED;
        Order actual = orderService.updateStatus(id, status);
        Assertions.assertEquals(status, actual.getStatus());
        Assertions.assertNotNull(actual.getEndDate());
    }

    @Test
    void calculatePrice_Ok() {
        Long id = 2L;
        Order order = new Order();
        order.setCar(new Car());
        order.getCar().setOwner(new Owner());
        order.getCar().getOwner().setOrders(List.of(new Order(), new Order()));

        Commodity commodity = new Commodity();
        commodity.setPrice(new BigDecimal(100));
        order.setCommodities(List.of(commodity));

        Favor favor = new Favor();
        favor.setPrice(new BigDecimal(200));
        order.setFavors(List.of(favor));
        Mockito.when(orderRepository.getReferenceById(id)).thenReturn(order);

        BigDecimal expected = new BigDecimal("290");
        BigDecimal actual = orderService.calculatePrice(id);
        Assertions.assertEquals(expected, actual.round(new MathContext(5)));
    }

    private void updateStatusTest(Long id) {
        Order order = new Order();
        order.setStatus(Order.Status.PROGRESS);
        order.setFavors(Collections.emptyList());
        Mockito.when(orderRepository.getReferenceById(id)).thenReturn(order);
        Mockito.when(orderRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
    }
}