package com.carservice.cardemo.car.impl;

import com.carservice.cardemo.car.Car;
import com.carservice.cardemo.car.CarService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public boolean deleteCarById(Long id) {
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()){
            Car car = iterator.next();
            if (car.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCar(Long id, Car updatedCar) {
        for (Car car : cars){
            if (car.getId().equals(id)) {
                car.setModel(updatedCar.getModel());
                car.setYear(updatedCar.getYear());
                return true;

            }
        }
        return false;
    }
}
