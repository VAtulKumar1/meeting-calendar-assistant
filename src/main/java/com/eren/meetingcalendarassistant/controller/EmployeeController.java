package com.eren.meetingcalendarassistant.controller;


import com.eren.meetingcalendarassistant.entities.Employee;
import com.eren.meetingcalendarassistant.entities.Meeting;
import com.eren.meetingcalendarassistant.service.EmployeeService;
import com.eren.meetingcalendarassistant.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add-employee")
    public ResponseEntity<Employee> addEmployee(@RequestParam String employeeName) {
        Employee employee = employeeService.addEmployee(employeeName);
        return ResponseEntity.ok(employee);
    }
}
