package com.example.EmployeeLeave.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeaveTypeNotFoundException extends RuntimeException {

    public LeaveTypeNotFoundException(Long id) {
        super("Leave Type with ID " + id + " not found");
    }
}
