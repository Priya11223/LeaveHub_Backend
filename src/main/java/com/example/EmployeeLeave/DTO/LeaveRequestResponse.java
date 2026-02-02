package com.example.EmployeeLeave.DTO;

import com.example.EmployeeLeave.enums.ReqStatus;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class LeaveRequestResponse {
    private Long id;
    private String leaveType;
    private LocalDate start;
    private LocalDate end;
    private Integer noDays;
    private LocalDate appliedDate;
    private ReqStatus status;
}
