package com.example.EmployeeLeave.Controller;

import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveRequest;
import com.example.EmployeeLeave.Service.EmployeeService;
import com.example.EmployeeLeave.Service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class ManagerController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private EmployeeService employeeService;

    //DONE
    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers() throws Exception {
        Employee manager = employeeService.findUserByJwt();
        return new ResponseEntity<>(employeeService.getAllEmp(), HttpStatus.OK);
    }

    //DONE
    @GetMapping("/users/pending")
    public ResponseEntity<?> getAllUsersPendingReq() throws Exception {
        Employee manager = employeeService.findUserByJwt();
        return new ResponseEntity<>(leaveRequestService.getAllPendingReq(), HttpStatus.OK);
    }

    @PutMapping("/requests/{leaveReqId}/approve")
    public ResponseEntity<?> approve(@PathVariable Long leaveReqId) throws Exception {
        Employee manager = employeeService.findUserByJwt();
        LeaveRequest leaveRequest = leaveRequestService.approveReq(leaveReqId);
        return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
    }

    @PutMapping("/requests/{leaveRequestId}/reject")
    public ResponseEntity<?> reject(@PathVariable Long leaveRequestId) throws Exception {
        Employee manager = employeeService.findUserByJwt();
        LeaveRequest leaveRequest = leaveRequestService.rejectReq(leaveRequestId);
        return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
    }
}
