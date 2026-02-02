package com.example.EmployeeLeave.Repository;

import com.example.EmployeeLeave.Modal.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepo extends JpaRepository<LeaveType, Long> {
    public LeaveType findByName(String leaveType);
}
