package com.example.meetingapp.controller

import com.example.meetingapp.domain.Member
import com.example.meetingapp.model.MeetingRequest
import com.example.meetingapp.service.MeetingService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/meeting")
class MeetingController(
    private val meetingService: MeetingService
) {

    @PostMapping
    fun createMeeting(
        @RequestBody meetingRequest: MeetingRequest,
        @AuthenticationPrincipal member: Member
    ) = meetingService.createMeeting(member, meetingRequest)

    @GetMapping
    fun readAllMeeting() = meetingService.readAllMeeting()

    @PutMapping("/{id}")
    fun updateMeeting(
        @PathVariable id: Long,
        @RequestBody meetingRequest: MeetingRequest,
    ) = meetingService.updateMeeting(id, meetingRequest)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMeeting(
        @PathVariable id: Long
    ){
        meetingService.deleteMeeting(id)
    }

//    // 나의 모임 조회
//    @GetMapping("/mine")
//    fun readMyMeeting(
//        @AuthenticationPrincipal member: Member
//    ) = null
//
//    // 모임 이름 검색
//    @GetMapping()
//    fun search(
//        @RequestParam search: String
//    ) = null

}