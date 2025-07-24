package com.carservice.cardemo.exception;

public class CarNotFound extends RuntimeException {
    public CarNotFound(Long id) {
        super("Car with ID " + id + " not found.");
    }
}
