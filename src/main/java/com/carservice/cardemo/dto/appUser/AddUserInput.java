package com.carservice.cardemo.dto.appUser;

import com.carservice.cardemo.dto.role.RoleDTO;
import lombok.Data;

import java.util.List;


@Data
public class AddUserInput {
    private String username;
    private String password;
    private List<RoleDTO> roles;
}
