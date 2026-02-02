package com.example.EmployeeLeave.Repository;

import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveRequest;
import com.example.EmployeeLeave.Modal.LeaveType;
import com.example.EmployeeLeave.enums.ReqStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(ReqStatus status);
    List<LeaveRequest> findByEmployee(Employee emp);
}
