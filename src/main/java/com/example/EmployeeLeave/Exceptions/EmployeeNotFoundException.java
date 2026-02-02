package com.example.EmployeeLeave.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String email) {
        super("User not found with email " + email);
    }
    public EmployeeNotFoundException(Long id) {
        super("User not found with id " + id);
    }
}
