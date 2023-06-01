package com.example.meetingapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeetingAppApplication

fun main(args: Array<String>) {
    runApplication<MeetingAppApplication>(*args)
}
