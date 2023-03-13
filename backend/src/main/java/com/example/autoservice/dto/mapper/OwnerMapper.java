package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.request.OwnerRequestDto;
import com.example.autoservice.dto.response.OwnerResponseDto;
import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.repository.CarRepository;
import com.example.autoservice.repository.OrderRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;

    public OwnerMapper(CarRepository carRepository, OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
    }

    public Owner mapToModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setCars(dto.getCarIds().stream()
                .map(carRepository::getReferenceById)
                .collect(Collectors.toList()));
        owner.setOrders(dto.getOrderIds().stream()
                .map(orderRepository::getReferenceById)
                .collect(Collectors.toList()));
        return owner;
    }

    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto dto = new OwnerResponseDto();
        dto.setId(owner.getId());
        dto.setCarIds(owner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        dto.setOrderIds(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
