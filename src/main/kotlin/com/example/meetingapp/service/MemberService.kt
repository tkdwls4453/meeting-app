package com.example.meetingapp.service

import com.example.meetingapp.domain.Member
import com.example.meetingapp.exception.NotFoundException
import com.example.meetingapp.exception.UserExistsException
import com.example.meetingapp.model.*
import com.example.meetingapp.repository.MemberRepository
import com.example.meetingapp.utils.JwtUtil
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {
    fun createMember(request: SignUpRequest): MemberResponse {
        val member: Member = Member(
            id = null,
            username = request.username,
            password = request.password,
            nickname = request.nickname
        )

        if(existsMember(member.username)){

            throw UserExistsException()
        }

        member.updatePassword(passwordEncoder.encode(member.password))
        val savedMember = memberRepository.save(member);

        return savedMember.toResponse()
    }

    fun existsMember(username: String): Boolean{
        return memberRepository.existsByUsername(username)
    }

    fun login(request: LoginRequest): LoginResponse {

        val member: Member = memberRepository.findByUsername(request.username) ?:
        throw NotFoundException("존재하지 않는 유저입니다.")

        if(!passwordEncoder.matches(request.password, member.password)){
            throw NotFoundException("잘못된 비밀번호 입니다.")
        }

        val token: String = jwtUtil.createToken(request.username);

        return LoginResponse(200, token)
    }
}