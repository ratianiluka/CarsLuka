package com.carservice.cardemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public class GlobalException {
    @ExceptionHandler(CarNotFound.class)
    public ResponseEntity<String> handleCarNotFound(CarNotFound exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception exception) {
        return new ResponseEntity<>("Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
