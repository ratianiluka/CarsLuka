package com.carservice.cardemo.repository;

import com.carservice.cardemo.model.AppUser;
import org.springframework.validation.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
