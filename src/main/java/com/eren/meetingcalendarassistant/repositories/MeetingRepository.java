package com.eren.meetingcalendarassistant.repositories;

import com.eren.meetingcalendarassistant.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
}
