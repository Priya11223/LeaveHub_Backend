package com.example.EmployeeLeave.Controller;

import com.example.EmployeeLeave.DTO.ApplyLeaveReq;
import com.example.EmployeeLeave.DTO.LeaveRequestResponse;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveRequest;
import com.example.EmployeeLeave.Service.EmployeeService;
import com.example.EmployeeLeave.Service.LeaveRequestService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private EmployeeService employeeService;

    //Done
    @PostMapping("/apply")
    public ResponseEntity<?> ApplyLeave (@RequestBody ApplyLeaveReq req) throws Exception{
        Employee emp = employeeService.findUserByJwt();
        LeaveRequest leaveRequest = leaveRequestService.createRequest(req, emp);
        return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
    }

    //Done
    @GetMapping("/my")
    public ResponseEntity<?> getAllReq() throws Exception{
        Employee emp = employeeService.findUserByJwt();
        List<LeaveRequestResponse> reqs = leaveRequestService.getAllEmpRequests(emp);
        return new ResponseEntity<>(reqs, HttpStatus.OK);
    }

    //Done
    @GetMapping("/pending")
    public ResponseEntity<?> getAllPendingReq() throws Exception{
        Employee emp = employeeService.findUserByJwt();
        List<LeaveRequest> lst = leaveRequestService.getAllEmpPendingReq(emp);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
}
