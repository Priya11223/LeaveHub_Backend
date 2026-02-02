package com.example.EmployeeLeave.DTO;

import com.example.EmployeeLeave.enums.Roles;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String jwt;
    private String email;
    private Roles role;
}
