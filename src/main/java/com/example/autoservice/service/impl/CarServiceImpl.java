package com.example.autoservice.service.impl;

import com.example.autoservice.model.Car;
import com.example.autoservice.repository.CarRepository;
import com.example.autoservice.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car get(Long id) {
        return carRepository.getReferenceById(id);
    }

    @Override
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }
}
