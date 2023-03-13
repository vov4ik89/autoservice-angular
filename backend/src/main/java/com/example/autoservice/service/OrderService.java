package com.example.autoservice.service;

import com.example.autoservice.model.Commodity;
import com.example.autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order get(Long id);

    Order add(Order order);

    Order update(Order order);

    Order addCommodity(Long orderId, Commodity commodity);

    Order updateStatus(Long id, Order.Status status);

    BigDecimal calculatePrice(Long orderId);
}
