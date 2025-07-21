package com.carservice.cardemo.car;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Car>> findAll(){
        return new ResponseEntity<>(carservice.findAll(), HttpStatus.OK);
    }

    @PostMapping("/cars")
    public ResponseEntity<String> createCar(@RequestBody Car car){
        carservice.createCar(car);
        return new ResponseEntity<>( "Car added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Car car = carservice.getCarById(id);
        if (car != null)
            return new ResponseEntity<>(car, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}


