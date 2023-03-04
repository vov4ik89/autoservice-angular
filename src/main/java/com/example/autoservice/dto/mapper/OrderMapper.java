package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.request.OrderRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.model.Commodity;
import com.example.autoservice.model.Favor;
import com.example.autoservice.model.Order;
import com.example.autoservice.repository.CommodityRepository;
import com.example.autoservice.service.CarService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final CommodityRepository commodityRepository;
    private final CarService carService;

    public OrderMapper(CommodityRepository commodityRepository,
                       CarService carService) {
        this.commodityRepository = commodityRepository;
        this.carService = carService;
    }

    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setDescription(dto.getDescription());
        order.setStatus(dto.getStatus());
        order.setCar(carService.get(dto.getCarId()));
        order.setPrice(dto.getPrice());
        order.setCommodities(dto.getCommodityIds().stream()
                .map(commodityRepository::getReferenceById)
                .collect(Collectors.toList()));
        return order;
    }

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setDescription(order.getDescription());
        dto.setStatus(order.getStatus());
        dto.setCarId(order.getCar().getId());
        dto.setPrice(order.getPrice());
        dto.setAcceptDate(order.getAcceptDate());
        dto.setEndDate(order.getEndDate());
        dto.setCommodityIds(order.getCommodities().stream()
                .map(Commodity::getId)
                .collect(Collectors.toList()));
        dto.setFavorIds(order.getFavors().stream()
                .map(Favor::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
