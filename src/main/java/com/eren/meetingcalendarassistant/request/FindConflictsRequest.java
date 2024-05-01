package com.eren.meetingcalendarassistant.request;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FindConflictsRequest {


    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> employeeIds;
}
