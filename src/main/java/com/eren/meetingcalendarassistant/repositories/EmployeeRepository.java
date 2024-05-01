package com.eren.meetingcalendarassistant.repositories;

import com.eren.meetingcalendarassistant.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
