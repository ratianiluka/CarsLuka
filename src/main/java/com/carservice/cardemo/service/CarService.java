package com.carservice.cardemo.service;

import com.carservice.cardemo.model.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car createCar(Car car);

    Car getCarById(Long id);

    boolean deleteCarById(Long id);

    Car updateCar(Long id, Car updatedCar);

}
