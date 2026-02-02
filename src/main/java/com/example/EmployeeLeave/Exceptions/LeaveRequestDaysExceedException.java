package com.example.EmployeeLeave.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LeaveRequestDaysExceedException extends RuntimeException {
    public LeaveRequestDaysExceedException(int days) {
        super("REQUEST asking for "+ days + " extra leaves from Balance");
    }
}
