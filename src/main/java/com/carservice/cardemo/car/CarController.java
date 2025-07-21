package com.carservice.cardemo.car;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {
    private CarService carservice;

    public CarController(CarService carservice) {
        this.carservice = carservice;
    }

    @GetMapping("/cars")
    public List<Car> findAll(){
        return carservice.findAll();
    }

    @PostMapping("/cars")
    public String createCar(@RequestBody Car car){
        carservice.createCar(car);
        return "Car added successfully";
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Long id){
        Car car = carservice.getCarById(id);
        if (car != null)
            return car;
        return new Car(1L, 0, "TestModel");
    }
}
