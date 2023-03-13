package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.request.CarRequestDto;
import com.example.autoservice.dto.response.CarResponseDto;
import com.example.autoservice.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setNumber(dto.getNumber());
        return car;
    }

    public CarResponseDto mapToDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        dto.setId(car.getId());
        dto.setOwnerId(car.getOwner().getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setNumber(car.getNumber());
        return dto;
    }
}
