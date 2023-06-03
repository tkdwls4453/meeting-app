package com.example.meetingapp.controller


import com.example.meetingapp.model.LoginRequest
import com.example.meetingapp.model.SignUpRequest
import com.example.meetingapp.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/join")
    fun singUP(
        @RequestBody request: SignUpRequest
    ) = memberService.createMember(request);

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ) = memberService.login(request);
}