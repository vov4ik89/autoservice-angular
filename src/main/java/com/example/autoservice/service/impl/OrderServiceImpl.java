package com.example.autoservice.service.impl;

import com.example.autoservice.model.Commodity;
import com.example.autoservice.model.Favor;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.repository.OrderRepository;
import com.example.autoservice.service.MasterService;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.OwnerService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final double COMMODITY_DISCOUNT = 0.01;
    private static final double MAINTENANCE_DISCOUNT = 0.02;
    private final MasterService masterService;
    private final OrderRepository orderRepository;
    private final OwnerService ownerService;

    public OrderServiceImpl(MasterService masterService,
                            OrderRepository orderRepository,
                            OwnerService ownerService) {
        this.masterService = masterService;
        this.orderRepository = orderRepository;
        this.ownerService = ownerService;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order get(Long id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public Order add(Order order) {
        order.setAcceptDate(LocalDateTime.now());
        orderRepository.save(order);
        Owner owner = order.getCar().getOwner();
        owner.getOrders().add(order);
        ownerService.update(owner);
        return order;
    }

    @Override
    public Order update(Order order) {
        Order oldOrder = get(order.getId());
        order.setAcceptDate(oldOrder.getAcceptDate());
        order.setFavors(oldOrder.getFavors());
        order.setPrice(oldOrder.getPrice());
        order.setEndDate(oldOrder.getEndDate());
        return orderRepository.save(order);
    }

    @Override
    public Order addCommodity(Long orderId, Commodity commodity) {
        Order order = orderRepository.getReferenceById(orderId);
        order.getCommodities().add(commodity);
        return update(order);
    }

    @Override
    public Order updateStatus(Long id, Order.Status status) {
        Order order = orderRepository.getReferenceById(id);
        if (status.equals(Order.Status.COMPLETED)
                || status.equals(Order.Status.NOT_COMPLETED)) {
            order.setEndDate(LocalDateTime.now());
            List<Master> masters = order.getFavors()
                    .stream()
                    .map(Favor::getMaster)
                    .distinct()
                    .peek(master -> master.getCompletedOrders().add(order))
                    .toList();
            masterService.update(masters);
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public BigDecimal calculatePrice(Long orderId) {
        Order order = orderRepository.getReferenceById(orderId);
        BigDecimal price = calculatePriceAfterDiscount(order);
        order.setPrice(price);
        update(order);
        return price;
    }

    private BigDecimal calculatePriceAfterDiscount(Order order) {
        int amountOrders = order.getCar().getOwner().getOrders().size();
        double commodityDiscount = amountOrders * COMMODITY_DISCOUNT;
        double maintenanceDiscount = amountOrders * MAINTENANCE_DISCOUNT;
        BigDecimal commodityPriceAfterDiscount = order.getCommodities().stream()
                .map(Commodity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(1.0 - commodityDiscount));
        if (order.getFavors().size() > 0) {
            return order.getFavors().stream()
                    .map(Favor::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .multiply(new BigDecimal(1.0 - maintenanceDiscount))
                    .add(commodityPriceAfterDiscount);
        }
        return commodityPriceAfterDiscount;
    }
}
