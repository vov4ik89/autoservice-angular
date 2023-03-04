package com.example.autoservice.service;

import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import java.util.List;

public interface OwnerService {
    List<Owner> getAll();

    Owner get(Long id);

    Owner add(Owner owner);

    Owner update(Owner owner);

    List<Order> getOrders(Long id);
}
