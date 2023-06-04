package com.example.meetingapp.utils

import io.jsonwebtoken.*
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(private val userDetailsService: UserDetailsService) {

    // 나중에 숨김 처리
    private var secretKey = "thisissecretkey"

    // 토큰 유효시간 30분 설정
    private val tokenValidTime = 30 * 60 * 1000L

    private val logger = KotlinLogging.logger{}
    @PostConstruct
    protected fun init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(userPk: String): String{
        // JWT payload 저장 단위
        val claims: Claims = Jwts.claims().setSubject(userPk)

        claims["userPk"] = userPk

        val now = Date()


        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAuthentication(token: String): Authentication{
        val userDetails = userDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUserPk(token: String): String{
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String?{
        logger.info{request.getHeader("Authorization")}
        return request.getHeader("Authorization")
    }

    fun validateToken(token: String): Boolean{
        return try{
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        }catch (e: Exception){
            false
        }
    }
}