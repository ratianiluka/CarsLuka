package com.carservice.cardemo.service.impl;

import com.carservice.cardemo.model.Car;
import com.carservice.cardemo.service.CarService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> cars = new ArrayList<>();
    private Long nextId = 1L;


    public CarServiceImpl(){
        cars.add(new Car(nextId++, "Mercedes C63", 2015));
        cars.add(new Car(nextId++, "BMW F10", 2016));
        cars.add(new Car(nextId++, "Toyota Camry", 2017));
        cars.add(new Car(nextId++, "Audi A8", 2018));
        cars.add(new Car(nextId++, "Honda Civic", 2019));
        cars.add(new Car(nextId++, "Nissan Altima", 2020));
        cars.add(new Car(nextId++, "Porsche Macan", 2021));


    }


    @Override
    public List<Car> findAll() {
        return cars;
    }



    @Override
    public Car createCar(Car car) {
        car.setId(nextId++);
        cars.add(car);
        return car;

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
    public Car updateCar(Long id, Car updatedCar) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                car.setModel(updatedCar.getModel());
                car.setYear(updatedCar.getYear());
                return car;
            }
        }
        return null;
    }
}
