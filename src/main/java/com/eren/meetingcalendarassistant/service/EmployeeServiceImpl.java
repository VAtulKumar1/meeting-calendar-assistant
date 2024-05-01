package com.eren.meetingcalendarassistant.service;


import com.eren.meetingcalendarassistant.entities.Employee;
import com.eren.meetingcalendarassistant.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(String employeeName) {
        Employee employee = new Employee();
        employee.setName(employeeName);

        return employeeRepository.save(employee);
    }
}
