package com.example.meetingapp.domain

import com.example.meetingapp.domain.enums.MemberRole
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

@Entity
class Member(
    username: String,
    password: String,
    nickname: String
) : BaseEntity(), UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    private var username: String = username

  
    @Column(nullable = false)
    private var password: String = password


    @Column(nullable = false)
    var nickname: String = nickname

    fun updatePassword(password: String){
        this.password = password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles = mutableSetOf<MemberRole>(MemberRole.USER)
        return roles.stream().map{ role -> SimpleGrantedAuthority("ROLE_$role")}.collect(Collectors.toSet())
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}