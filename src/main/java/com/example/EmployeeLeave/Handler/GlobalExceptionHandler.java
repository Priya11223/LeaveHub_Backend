package com.example.EmployeeLeave.Handler;

import com.example.EmployeeLeave.Exceptions.EmployeeNotFoundException;
import com.example.EmployeeLeave.Exceptions.LeaveRequestDaysExceedException;
import com.example.EmployeeLeave.Exceptions.LeaveRequestNotFoundException;
import com.example.EmployeeLeave.Exceptions.LeaveTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LeaveRequestNotFoundException.class)
    public ResponseEntity<?> handleLeaveRequestNotFound(
            LeaveRequestNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleInsufficientBalance(
            EmployeeNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(LeaveTypeNotFoundException.class)
    public ResponseEntity<?> handleLeaveTypeNotFound(
            LeaveTypeNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(LeaveRequestDaysExceedException.class)
    public ResponseEntity<?> handleLeaveRequestExceedBalance(
            LeaveRequestDaysExceedException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}

