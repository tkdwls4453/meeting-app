package com.example.meetingapp.service

import com.example.meetingapp.domain.Meeting
import com.example.meetingapp.domain.Member
import com.example.meetingapp.exception.NotFoundException
import com.example.meetingapp.model.MeetingRequest
import com.example.meetingapp.model.MeetingResponse
import com.example.meetingapp.model.toResponse
import com.example.meetingapp.repository.MeetingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class MeetingService(
    private val meetingRepository: MeetingRepository
) {

    @Transactional
    fun createMeeting(member: Member, meetingRequest: MeetingRequest): MeetingResponse {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        val meeting: Meeting = Meeting(
            name = meetingRequest.name,
            totalParticipationNum = meetingRequest.totalParticipationNum,
            description = meetingRequest.description,
            startDate = LocalDate.parse(meetingRequest.startDate, formatter),
            endDate = LocalDate.parse(meetingRequest.endDate, formatter),
            owner = member
        )

        member.createdMeeting.add(meeting)
        return meetingRepository.save(meeting).toResponse()
    }

    @Transactional(readOnly = true)
    fun readAllMeeting(): MutableList<MeetingResponse> {
        val responseList: MutableList<MeetingResponse> = mutableListOf()
        val findAll = meetingRepository.findAll()

        for(meeting in findAll){
            responseList.add(meeting.toResponse())
        }
        return responseList
    }

    @Transactional
    fun updateMeeting(id: Long, meetingRequest: MeetingRequest): MeetingResponse {
        val meeting: Meeting = meetingRepository.findByIdOrNull(id) ?: throw NotFoundException("존재하지 않는 모임입니다")
        meeting.update(meetingRequest)
        return meeting.toResponse()
    }

    @Transactional
    fun deleteMeeting(id: Long) {
        meetingRepository.deleteById(id)
    }


}
