package com.example.EmployeeLeave.Controller;

import com.example.EmployeeLeave.DTO.UserDto;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Done
    @PostMapping("/signIn")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload)
    {
        String name = payload.get("fullName");
        String email = payload.get("email");
        String pass = payload.get("password");

        UserDto details = employeeService.createUser(name, email, pass);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/signIn/manager")
    public ResponseEntity<?> signupForManager(@RequestBody Map<String, String> payload)
    {
        String name = payload.get("fullName");
        String email = payload.get("email");
        String pass = payload.get("password");

        UserDto details = employeeService.createManager(name, email, pass);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    //Done
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload){
        try{
            String email = payload.get("email");
            String pass = payload.get("password");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pass));
            UserDto details = employeeService.getInfoByEmail(email);
            return new ResponseEntity<>(details, HttpStatus.OK);
        }
        catch (Exception e){
            Map<String, String> detail = new HashMap<>();
            detail.put("message", "fail - Invalid Credentials");
            return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
        }
    }
}
