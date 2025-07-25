package com.carservice.cardemo.controller;

import com.carservice.cardemo.model.Car;
import com.carservice.cardemo.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private CarService carservice;

    public CarController(CarService carservice) {
        this.carservice = carservice;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll(){
        return new ResponseEntity<>(carservice.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car createdCar = carservice.createCar(car);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Car car = carservice.getCarById(id);
        if (car != null)
            return new ResponseEntity<>(car, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        boolean deleted = carservice.deleteCarById(id);
        if (deleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Car updated = carservice.updateCar(id, updatedCar);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}


