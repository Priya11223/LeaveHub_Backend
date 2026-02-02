package com.example.EmployeeLeave.Controller;

import com.example.EmployeeLeave.DTO.LeaveBalanceDto;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Service.EmployeeService;
import com.example.EmployeeLeave.Service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/balance")
public class LeaveBalanceContoller {
    @Autowired
    LeaveBalanceService leaveBalanceService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBalances() throws Exception {
        Employee emp = employeeService.findUserByJwt();
        List<LeaveBalanceDto> ret = leaveBalanceService.getBalancesOfUser(emp);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
