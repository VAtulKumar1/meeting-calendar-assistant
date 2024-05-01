package com.eren.meetingcalendarassistant.service;

import com.eren.meetingcalendarassistant.entities.Employee;
import com.eren.meetingcalendarassistant.entities.Meeting;
import com.eren.meetingcalendarassistant.request.BookMeetingRequest;
import com.eren.meetingcalendarassistant.request.FindConflictsRequest;
import com.eren.meetingcalendarassistant.request.FindFreeSlotsRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingService {

    public Meeting bookMeeting(Long employeeId, BookMeetingRequest request);

    public List<LocalDateTime[]> findFreeSlots(Long employeeId1, Long employeeId2, FindFreeSlotsRequest request);

    public List<Employee> findConflicts(FindConflictsRequest request);
}
