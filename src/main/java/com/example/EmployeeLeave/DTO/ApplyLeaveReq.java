package com.example.EmployeeLeave.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplyLeaveReq {
    private String leaveType;
    private LocalDate start;
    private LocalDate end;
}
