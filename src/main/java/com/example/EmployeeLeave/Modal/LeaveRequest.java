package com.example.EmployeeLeave.Modal;

import com.example.EmployeeLeave.enums.ReqStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveType leaveType;

    private LocalDate applyDate;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer numberOfDays;

    @Enumerated(EnumType.STRING)
    private ReqStatus status;
}
