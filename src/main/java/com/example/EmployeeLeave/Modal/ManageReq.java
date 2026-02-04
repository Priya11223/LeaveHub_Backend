package com.example.EmployeeLeave.Modal;

import com.example.EmployeeLeave.enums.ReqStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ManageReq {
    private Long id;
    private String employeeName;
    private Long employeeId;
    private String leaveType;
    private LocalDate start;
    private LocalDate end;
    private Integer noDays;
    private LocalDate appliedDate;
}
