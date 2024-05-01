package com.eren.meetingcalendarassistant.controller;

import com.eren.meetingcalendarassistant.entities.Employee;
import com.eren.meetingcalendarassistant.entities.Meeting;
import com.eren.meetingcalendarassistant.request.BookMeetingRequest;
import com.eren.meetingcalendarassistant.request.FindConflictsRequest;
import com.eren.meetingcalendarassistant.request.FindFreeSlotsRequest;
import com.eren.meetingcalendarassistant.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/meeting-assistant")
public class CalendarController {
    private final MeetingService meetingService;

    @Autowired
    public CalendarController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/book-meeting")
    public ResponseEntity<Meeting> bookMeeting(@RequestParam Long employeeId,
                                               @RequestBody BookMeetingRequest request) {
        Meeting meeting = meetingService.bookMeeting(employeeId, request);
        return ResponseEntity.ok(meeting);
    }

    @GetMapping("/free-slots")
    public ResponseEntity<List<LocalDateTime[]>> findFreeSlots(@RequestParam Long employeeId1,
                                                               @RequestParam Long employeeId2,
                                                               @RequestBody FindFreeSlotsRequest request) {
        List<LocalDateTime[]> freeSlots = meetingService.findFreeSlots(employeeId1, employeeId2, request);
        return ResponseEntity.ok(freeSlots);
    }

    @PostMapping("/conflicts")
    public ResponseEntity<List<Employee>> findConflicts(@RequestBody FindConflictsRequest request) {
        List<Employee> conflictingEmployees = meetingService.findConflicts(request);
        return ResponseEntity.ok(conflictingEmployees);
    }
}
