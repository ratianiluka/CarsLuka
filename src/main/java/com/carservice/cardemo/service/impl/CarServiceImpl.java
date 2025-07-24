package com.carservice.cardemo.service.impl;

import com.carservice.cardemo.model.Car;
import com.carservice.cardemo.service.CarService;
import org.springframework.stereotype.Service;
import com.carservice.cardemo.exception.CarNotFound;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> cars = new ArrayList<>();
    private Long nextId = 1L;


    public CarServiceImpl(){
        cars.add(new Car(nextId++, "Mercedes C63", LocalDate.of(2015, 5, 10)));
        cars.add(new Car(nextId++, "BMW F10", LocalDate.of(2016, 6, 20)));
        cars.add(new Car(nextId++, "Toyota Camry", LocalDate.of(2017, 3, 15)));
        cars.add(new Car(nextId++, "Audi A8", LocalDate.of(2018, 7, 25)));
        cars.add(new Car(nextId++, "Honda Civic", LocalDate.of(2019, 9, 5)));
        cars.add(new Car(nextId++, "Nissan Altima", LocalDate.of(2020, 11, 12)));
        cars.add(new Car(nextId++, "Porsche Macan", LocalDate.of(2021, 1, 30)));

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
            throw new CarNotFound(id);
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
        throw new CarNotFound(id);
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
        throw new CarNotFound(id);
    }
}
