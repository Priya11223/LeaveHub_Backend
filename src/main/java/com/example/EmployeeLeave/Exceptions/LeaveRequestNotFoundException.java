package com.example.EmployeeLeave.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeaveRequestNotFoundException extends RuntimeException {

    public LeaveRequestNotFoundException(Long id) {
        super("Leave request not found with id: " + id);
    }
}
