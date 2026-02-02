package com.example.EmployeeLeave.Modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer maxAllowedPerYear;
}