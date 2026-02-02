package com.example.EmployeeLeave.DTO;

import lombok.Data;

@Data
public class LeaveBalanceDto {
    private String leaveType;
    private Integer remainingDays;
    private Integer maxDays;
}
