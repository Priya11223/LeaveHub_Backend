package com.example.EmployeeLeave.Service;

import com.example.EmployeeLeave.DTO.UserDto;
import com.example.EmployeeLeave.Exceptions.EmployeeNotFoundException;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Repository.EmployeeRepo;
import com.example.EmployeeLeave.Utils.JwtUtils;
import com.example.EmployeeLeave.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto createUser(String name, String email, String pass){
        Employee emp = new Employee();
        emp.setEmail(email);
        emp.setName(name);
        emp.setPass(passwordEncoder.encode(pass));
        emp.setRole(Roles.USER);
        Employee employee = employeeRepo.save(emp);
        leaveBalanceService.createBalanceForUser(employee);

        String jwt = jwtUtils.generateToken(email);
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setRole(Roles.USER);
        dto.setEmail(email);
        dto.setJwt(jwt);
        return dto;
    }

    public UserDto createManager(String name, String email, String pass){
        Employee emp = new Employee();
        emp.setEmail(email);
        emp.setName(name);
        emp.setPass(passwordEncoder.encode(pass));
        emp.setRole(Roles.MANAGER);
        Employee employee = employeeRepo.save(emp);

        String jwt = jwtUtils.generateToken(email);
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setRole(Roles.MANAGER);
        dto.setJwt(jwt);
        dto.setEmail(email);
        return dto;
    }

    public Employee getEmp(Long id){
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return emp;
    }

    public UserDto getInfoByEmail(String email) {
        Employee emp = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(email));

        UserDto dto = new UserDto();
        dto.setName(emp.getName());
        dto.setRole(emp.getRole());
        dto.setEmail(emp.getEmail());
        String jwt = jwtUtils.generateToken(email);
        dto.setJwt(jwt);
        return dto;
    }

    public List<UserDto> getAllEmp() {
        List<Employee> lst =  employeeRepo.findAll();
        List<UserDto> res = new ArrayList<>();
        for(Employee emp : lst){
            if(emp.getRole().equals(Roles.MANAGER)) continue;
            UserDto dto = new UserDto();
            dto.setName(emp.getName());
            dto.setRole(emp.getRole());
            dto.setEmail(emp.getEmail());
            dto.setJwt("NONE");
            res.add(dto);
        }
        return res;
    }

    public Employee findUserByJwt() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return employeeRepo.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(email));
    }
}
