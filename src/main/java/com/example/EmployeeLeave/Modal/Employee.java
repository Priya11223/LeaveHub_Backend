package com.example.EmployeeLeave.Modal;

import com.example.EmployeeLeave.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pass;

    @Enumerated(EnumType.STRING)
    private Roles role;
}
