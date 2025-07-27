package com.carservice.cardemo.repository;

import com.carservice.cardemo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< Updated upstream
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

=======

public interface CarRepository extends JpaRepository<Car, Long> {
    // no extra methods needed for basic CRUD
>>>>>>> Stashed changes
}
