package com.example.EmployeeLeave.Repository;

import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveBalance;
import com.example.EmployeeLeave.Modal.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveBalanceRepo extends JpaRepository<LeaveBalance, Long> {
    List<LeaveBalance> findByEmployee(Employee emp);
    LeaveBalance findByEmployeeAndLeaveType(Employee emp, LeaveType type);
}
