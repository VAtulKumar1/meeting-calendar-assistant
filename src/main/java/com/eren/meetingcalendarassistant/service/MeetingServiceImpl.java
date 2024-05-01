package com.eren.meetingcalendarassistant.service;

import com.eren.meetingcalendarassistant.entities.Employee;
import com.eren.meetingcalendarassistant.entities.Meeting;
import com.eren.meetingcalendarassistant.repositories.EmployeeRepository;
import com.eren.meetingcalendarassistant.repositories.MeetingRepository;
import com.eren.meetingcalendarassistant.request.BookMeetingRequest;
import com.eren.meetingcalendarassistant.request.FindConflictsRequest;
import com.eren.meetingcalendarassistant.request.FindFreeSlotsRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final EmployeeRepository employeeRepository;
    private final MeetingRepository meetingRepository;

    public MeetingServiceImpl(EmployeeRepository employeeRepository, MeetingRepository meetingRepository) {
        this.employeeRepository = employeeRepository;
        this.meetingRepository = meetingRepository;
    }

    public Meeting bookMeeting(Long employeeId, BookMeetingRequest request) {
        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Meeting meeting = new Meeting();
        meeting.setOwner(employee);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        employee.addMeeting(meeting);
        meetingRepository.save(meeting);
        return meeting;
    }

    public List<LocalDateTime[]> findFreeSlots(Long employeeId1, Long employeeId2, FindFreeSlotsRequest request) {
        Employee employee1 = employeeRepository.findById(employeeId1)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Employee employee2 = employeeRepository.findById(employeeId2)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();
        int durationMinutes = request.getDurationMinutes();

        List<LocalDateTime[]> freeSlots = new ArrayList<>();
        LocalDateTime currentTime = startTime;

        while (currentTime.plusMinutes(durationMinutes).isBefore(endTime) || currentTime.plusMinutes(durationMinutes).isEqual(endTime)) {
            boolean isFreeForBoth = true;

            for (Meeting meeting : employee1.getCalendar()) {
                if (meeting.getStartTime().isBefore(currentTime.plusMinutes(durationMinutes)) &&
                        meeting.getEndTime().isAfter(currentTime)) {
                    isFreeForBoth = false;
                    break;
                }
            }

            if (isFreeForBoth) {
                for (Meeting meeting : employee2.getCalendar()) {
                    if (meeting.getStartTime().isBefore(currentTime.plusMinutes(durationMinutes)) &&
                            meeting.getEndTime().isAfter(currentTime)) {
                        isFreeForBoth = false;
                        break;
                    }
                }
            }

            if (isFreeForBoth) {
                freeSlots.add(new LocalDateTime[]{currentTime, currentTime.plusMinutes(durationMinutes)});
            }

            currentTime = currentTime.plusMinutes(60);
        }

        return freeSlots;
    }

    public List<Employee> findConflicts(FindConflictsRequest request) {

        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();
        List<Long> employeeIds = request.getEmployeeIds();
        List<Employee> conflictingEmployees = new ArrayList<>();

        for (Long employeeId : employeeIds) {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

            for (Meeting meeting : employee.getCalendar()) {
                if (meeting.getStartTime().isBefore(endTime) && meeting.getEndTime().isAfter(startTime)) {
                    conflictingEmployees.add(employee);
                    break;
                }
            }
        }

        return conflictingEmployees;
    }
}
