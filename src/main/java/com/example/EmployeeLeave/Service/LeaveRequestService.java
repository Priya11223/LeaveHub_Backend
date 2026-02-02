package com.example.EmployeeLeave.Service;

import com.example.EmployeeLeave.DTO.ApplyLeaveReq;
import com.example.EmployeeLeave.DTO.LeaveRequestResponse;
import com.example.EmployeeLeave.Exceptions.LeaveRequestDaysExceedException;
import com.example.EmployeeLeave.Exceptions.LeaveRequestNotFoundException;
import com.example.EmployeeLeave.Modal.Employee;
import com.example.EmployeeLeave.Modal.LeaveRequest;
import com.example.EmployeeLeave.Modal.LeaveType;
import com.example.EmployeeLeave.Repository.EmployeeRepo;
import com.example.EmployeeLeave.Repository.LeaveRequestRepo;
import com.example.EmployeeLeave.Repository.LeaveTypeRepo;
import com.example.EmployeeLeave.enums.ReqStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    // Could have send Dto for making response attack free and compact
    public LeaveRequest createRequest(ApplyLeaveReq req, Employee emp){
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(emp);

        LeaveType type = leaveTypeRepo.findByName(req.getLeaveType());

        leaveRequest.setLeaveType(type);
        leaveRequest.setStatus(ReqStatus.PENDING);
        long days = ChronoUnit.DAYS.between(req.getStart(), req.getEnd()) + 1;

        int remaining = leaveBalanceService.getRemainingDaysOfEmpType(emp, type);

        if(remaining < days)
            throw new LeaveRequestDaysExceedException(Math.toIntExact(days-remaining));

        leaveRequest.setNumberOfDays(Math.toIntExact(days));
        leaveRequest.setStartDate(req.getStart());
        leaveRequest.setEndDate(req.getEnd());
        leaveRequest.setApplyDate(LocalDate.now());

        return leaveRequestRepo.save(leaveRequest);
    }

    public List<LeaveRequestResponse> getAllEmpRequests(Employee emp) {
        List<LeaveRequest> reqs = leaveRequestRepo.findByEmployee(emp);
        List<LeaveRequestResponse> res = new ArrayList<>();
        for(LeaveRequest req : reqs){
            LeaveRequestResponse l = new LeaveRequestResponse();
            l.setLeaveType(req.getLeaveType().getName());
            l.setStart(req.getStartDate());
            l.setEnd(req.getEndDate());
            l.setNoDays(req.getNumberOfDays());
            l.setStatus(req.getStatus());
            l.setAppliedDate(req.getApplyDate());
            l.setId(req.getId());
            res.add(l);
        }
        return res;
    }

    public List<LeaveRequest> getAllEmpPendingReq(Employee emp) {
        List<LeaveRequest> requests = leaveRequestRepo.findByEmployee(emp);
        List<LeaveRequest> res = new ArrayList<>();
        for(LeaveRequest req : requests){
            if(req.getStatus() == ReqStatus.PENDING)
                res.add(req);
        }
        return res;
    }

    public List<LeaveRequest> getAllPendingReq(){
        return leaveRequestRepo.findByStatus(ReqStatus.PENDING);
    }

    // Update the Leave Balance
    public LeaveRequest approveReq(Long leaveRequestId) {
        LeaveRequest req = leaveRequestRepo.findById(leaveRequestId)
                .orElseThrow(() -> new LeaveRequestNotFoundException(leaveRequestId));

        if (req.getStatus() != ReqStatus.PENDING) {
            throw new IllegalStateException("Leave request already processed");
        }

        leaveBalanceService.reduceBalance(req.getEmployee(), req.getLeaveType(), req.getNumberOfDays());
        req.setStatus(ReqStatus.APPROVED);
        return leaveRequestRepo.save(req);
    }

    public LeaveRequest rejectReq(Long leaveRequestId) {
        LeaveRequest req = leaveRequestRepo.findById(leaveRequestId)
                .orElseThrow(() -> new LeaveRequestNotFoundException(leaveRequestId));

        if(req.getStatus() != ReqStatus.PENDING){
            throw new IllegalStateException("Leave request already processed");
        }

        req.setStatus(ReqStatus.REJECTED);
        return leaveRequestRepo.save(req);
    }
}
