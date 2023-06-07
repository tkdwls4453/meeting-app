package com.example.meetingapp.domain

import com.example.meetingapp.model.MeetingRequest
import jakarta.persistence.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Entity
class Meeting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var totalParticipationNum: Int = 2,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var startDate: LocalDate,

    @Column(nullable = false)
    var endDate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val owner: Member,

) : BaseEntity(){

    fun update(meetingRequest: MeetingRequest){
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE

        name = meetingRequest.name
        totalParticipationNum = meetingRequest.totalParticipationNum
        description = meetingRequest.description
        startDate = LocalDate.parse(meetingRequest.startDate, formatter)
        endDate = LocalDate.parse(meetingRequest.endDate, formatter)
    }
}