package com.example.EmployeeLeave.Controller;

import com.example.EmployeeLeave.DTO.LeaveTypeReq;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveType;
import com.example.EmployeeLeave.Service.EmployeeService;
import com.example.EmployeeLeave.Service.LeaveTypeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manage/api/LeaveType")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private EmployeeService employeeService;

    //DONE
    @GetMapping("/all")
    public ResponseEntity<?> getAllTypes() throws Exception{
        Employee manager = employeeService.findUserByJwt();
        List<LeaveType> lst = leaveTypeService.getALL();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    //DONE
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LeaveTypeReq req) throws Exception{
        Employee manager = employeeService.findUserByJwt();
        LeaveType type = leaveTypeService.createType(req);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    //DONE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id) throws Exception {
        Employee manager = employeeService.findUserByJwt();
        leaveTypeService.deleteType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
