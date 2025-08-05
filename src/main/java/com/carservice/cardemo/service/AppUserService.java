package com.carservice.cardemo.service;

import com.carservice.cardemo.dto.appUser.AddUserInput;
import com.carservice.cardemo.dto.appUser.AddUserOutput;

public interface AppUserService {
    AddUserOutput addUser(AddUserInput addUserInput);
}