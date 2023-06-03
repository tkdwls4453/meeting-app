package com.example.meetingapp.model

import com.example.meetingapp.domain.Member
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class SignUpRequest(
    val username: String,
    val password: String,
    val nickname: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class MemberResponse(
    val id: Long,
    val username: String,
    val nickname: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
)

data class LoginResponse(
    val code: Int,
    val token: String
)

fun Member.toResponse() = MemberResponse(
    id = id!!,
    username = username,
    nickname = nickname,
    createdAt = createdAt,
    updatedAt = updatedAt
)