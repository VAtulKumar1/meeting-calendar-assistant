package com.eren.meetingcalendarassistant.request;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Data
public class FindFreeSlotsRequest {

    private LocalDateTime startTime;
    private  LocalDateTime endTime;
    private int durationMinutes;
}
