package com.carservice.cardemo.car.impl;

import com.carservice.cardemo.car.Car;
import com.carservice.cardemo.car.CarService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> cars = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public void createCar(Car car) {
        car.setId(nextId++);
        cars.add(car);

    }

    @Override
    public Car getCarById(Long id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
            return null;
    }
}
