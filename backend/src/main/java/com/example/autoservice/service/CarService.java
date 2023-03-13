package com.example.autoservice.service;

import com.example.autoservice.model.Car;
import java.util.List;

public interface CarService {
    List<Car> getAll();

    Car get(Long id);

    Car add(Car car);

    Car update(Car car);
}
