package com.example.meetingapp.model

import com.example.meetingapp.domain.Meeting
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class MeetingRequest(
    val name: String,
    val totalParticipationNum: Int,
    val description: String,
    val startDate: String,
    val endDate: String
)

data class MeetingResponse(
    val id: Long,
    val name: String,
    val totalParticipationNum: Int,
    val description: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val startDate: LocalDate,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val endDate: LocalDate,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
)

fun Meeting.toResponse() = MeetingResponse(
    id = id!!,
    name = name,
    totalParticipationNum = totalParticipationNum,
    description = description,
    startDate = startDate,
    endDate = endDate,
    createdAt = createdAt,
    updatedAt = updatedAt
)