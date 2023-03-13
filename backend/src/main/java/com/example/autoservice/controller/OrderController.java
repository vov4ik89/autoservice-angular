package com.example.autoservice.controller;

import com.example.autoservice.dto.mapper.CommodityMapper;
import com.example.autoservice.dto.mapper.OrderMapper;
import com.example.autoservice.dto.request.CommodityRequestDto;
import com.example.autoservice.dto.request.OrderRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.model.Commodity;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final CommodityMapper commodityMapper;

    public OrderController(OrderMapper orderMapper,
                           OrderService orderService,
                           CommodityMapper commodityMapper) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.commodityMapper = commodityMapper;
    }

    @GetMapping
    @ApiOperation("Get all orders")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll()
                .stream()
                .map(orderMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get order by id")
    public OrderResponseDto get(@PathVariable Long id) {
        Order order = orderService.get(id);
        return orderMapper.mapToDto(order);
    }

    @PostMapping
    @ApiOperation("Add a new order")
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.mapToModel(requestDto);
        orderService.add(order);
        return orderMapper.mapToDto(order);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update order by id")
    public OrderResponseDto update(@PathVariable Long id,
                                   @RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.mapToModel(requestDto);
        order.setId(id);
        orderService.update(order);
        return orderMapper.mapToDto(order);
    }

    @PostMapping("/{id}/commodity")
    @ApiOperation("Add a new commodity to order by id")
    public OrderResponseDto addCommodities(@PathVariable Long id,
                                           @RequestBody CommodityRequestDto requestDto) {
        Commodity commodity = commodityMapper.mapToModel(requestDto);
        return orderMapper.mapToDto(orderService.addCommodity(id, commodity));
    }

    @GetMapping("/{id}/status")
    @ApiOperation("Update status by order id")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                         @RequestBody OrderRequestDto requestDto) {
        Order status = orderMapper.mapToModel(requestDto);
        orderService.updateStatus(id, status.getStatus());
        return orderMapper.mapToDto(status);
    }

    @GetMapping("/{id}/price")
    @ApiOperation("Calculate price by order id")
    public BigDecimal calculatePrice(@PathVariable Long id) {
        return orderService.calculatePrice(id);
    }
}
