package com.example.meetingapp.repository

import com.example.meetingapp.domain.Meeting
import org.springframework.data.jpa.repository.JpaRepository


interface MeetingRepository : JpaRepository<Meeting, Long>{
}