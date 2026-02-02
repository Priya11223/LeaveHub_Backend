package com.example.EmployeeLeave.Service;

import com.example.EmployeeLeave.DTO.LeaveBalanceDto;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveBalance;
import com.example.EmployeeLeave.Modal.LeaveType;
import com.example.EmployeeLeave.Repository.LeaveBalanceRepo;
import com.example.EmployeeLeave.Repository.LeaveTypeRepo;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveBalanceService {
    @Autowired
    private LeaveBalanceRepo leaveBalanceRepo;

    @Autowired
    private LeaveTypeService leaveTypeService;

    public List<LeaveBalanceDto> getBalancesOfUser(Employee emp){
        List<LeaveBalance> lst = leaveBalanceRepo.findByEmployee(emp);
        List<LeaveBalanceDto> ret = new ArrayList<>();
        for(LeaveBalance b : lst){
            LeaveBalanceDto dto = new LeaveBalanceDto();
            dto.setLeaveType(b.getLeaveType().getName());
            dto.setRemainingDays(b.getRemainingDays());
            dto.setMaxDays(b.getLeaveType().getMaxAllowedPerYear());
            ret.add(dto);
        }
        return ret;
    }

    public List<LeaveBalance> createBalanceForUser(Employee emp){
        List<LeaveType> types = leaveTypeService.getALL();
        List<LeaveBalance> balances = new ArrayList<>();
        for(LeaveType type : types){
            LeaveBalance b = new LeaveBalance();
            b.setEmployee(emp);
            b.setLeaveType(type);
            b.setRemainingDays(type.getMaxAllowedPerYear());
            leaveBalanceRepo.save(b);
            balances.add(b);
        }
        return balances;
    }

    public List<LeaveBalance> getAllBalancesForUser(Employee emp){
        return leaveBalanceRepo.findByEmployee(emp);
    }

    public int getRemainingDaysOfEmpType(Employee emp, LeaveType type) {
        List<LeaveBalance> balance = leaveBalanceRepo.findByEmployee(emp);
        for(LeaveBalance b : balance){
            if(b.getLeaveType().equals(type)) return b.getRemainingDays();
        }
        return 0;
    }

    public void reduceBalance(Employee employee,
                              LeaveType type,
                              int days) {
        LeaveBalance balance = leaveBalanceRepo.findByEmployeeAndLeaveType(employee, type);
        int remaining = balance.getRemainingDays();

        balance.setRemainingDays(remaining-days);
        leaveBalanceRepo.save(balance);
    }
}
