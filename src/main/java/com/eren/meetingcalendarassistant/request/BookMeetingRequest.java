package com.eren.meetingcalendarassistant.request;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Data
public class BookMeetingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
