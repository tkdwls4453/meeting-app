package com.example.meetingapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MeetingAppApplication

fun main(args: Array<String>) {
    runApplication<MeetingAppApplication>(*args)
}
