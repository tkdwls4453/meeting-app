package com.example.meetingapp.config

import com.example.meetingapp.filter.AuthenticationFilter
import com.example.meetingapp.utils.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtil: JwtUtil
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .httpBasic().disable()
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/join", "/login", "/logout", "/h2-console/**").permitAll()
            .requestMatchers("/api/**").hasRole("USER")
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(AuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
//            .logout()
//            .permitAll()
//            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
//            .invalidateHttpSession(true)
//            .deleteCookies("Authorization")
//            .logoutSuccessUrl("/logged_out")


        return http.build()
    }
}