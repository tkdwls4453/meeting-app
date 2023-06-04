package com.example.meetingapp.filter

import com.example.meetingapp.utils.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class AuthenticationFilter(
    private val jwtUtil: JwtUtil
) : GenericFilterBean(){

    private val logger = KotlinLogging.logger{}

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        // 헤더에서 jwt 받아옴
        val token: String? = jwtUtil.resolveToken((request as HttpServletRequest))

        if(token != null && jwtUtil.validateToken(token)){
            val authentication = jwtUtil.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request,response)
    }
}