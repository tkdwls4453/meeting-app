package com.example.meetingapp.repository

import com.example.meetingapp.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>{
    fun findByUsername(username: String) : Member?
    fun existsByUsername(username: String) : Boolean
}