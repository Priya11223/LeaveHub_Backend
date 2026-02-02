package com.example.EmployeeLeave.Modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveType leaveType;

    private Integer remainingDays;
}
