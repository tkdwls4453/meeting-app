package com.example.meetingapp.service

import com.example.meetingapp.exception.NotFoundException
import com.example.meetingapp.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val memberRepository: MemberRepository
) : UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByUsername(username) ?: throw NotFoundException("존재하지 않는 회원입니다.")
    }
}