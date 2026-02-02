package com.example.EmployeeLeave.Service;

import com.example.EmployeeLeave.DTO.LeaveTypeReq;
import com.example.EmployeeLeave.Exceptions.LeaveTypeNotFoundException;
import com.example.EmployeeLeave.Modal.LeaveType;
import com.example.EmployeeLeave.Repository.LeaveTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeService {
    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    public List<LeaveType> getALL(){
        return leaveTypeRepo.findAll();
    }

    public LeaveType createType(LeaveTypeReq req) {
         LeaveType type = new LeaveType();
         type.setName(req.getName());
         type.setMaxAllowedPerYear(req.getNos());
         return leaveTypeRepo.save(type);
    }

    public void deleteType(Long id) {
        LeaveType type = leaveTypeRepo.findById(id)
                .orElseThrow(() -> new LeaveTypeNotFoundException(id));
        leaveTypeRepo.deleteById(id);
    }
}
