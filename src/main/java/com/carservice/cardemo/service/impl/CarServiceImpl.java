package com.carservice.cardemo.service.impl;

import com.carservice.cardemo.model.Car;
import com.carservice.cardemo.repository.CarRepository;
import com.carservice.cardemo.service.CarService;
import org.springframework.stereotype.Service;
import com.carservice.cardemo.exception.CarNotFound;

<<<<<<< Updated upstream
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
=======
>>>>>>> Stashed changes
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
<<<<<<< Updated upstream
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
=======

    private final CarRepository carRepository;
>>>>>>> Stashed changes

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car getCarById(Long id) {
<<<<<<< Updated upstream
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
            throw new CarNotFound(id);
=======
        return carRepository.findById(id).orElse(null);
>>>>>>> Stashed changes
    }

    @Override
    public boolean deleteCarById(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        throw new CarNotFound(id);
    }

    @Override
    public Car updateCar(Long id, Car updatedCar) {
<<<<<<< Updated upstream
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                car.setModel(updatedCar.getModel());
                car.setYear(updatedCar.getYear());
                return car;
            }
        }
        throw new CarNotFound(id);
=======
        return carRepository.findById(id).map(car -> {
            car.setModel(updatedCar.getModel());
            car.setYear(updatedCar.getYear());
            return carRepository.save(car);
        }).orElse(null);
>>>>>>> Stashed changes
    }
}
