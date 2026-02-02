package com.example.EmployeeLeave.Service;

import com.example.EmployeeLeave.Exceptions.EmployeeNotFoundException;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepo uRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Employee user = uRepo.findByEmail(email)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(email)
                );
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPass(),
                authorities
        );
    }
}
