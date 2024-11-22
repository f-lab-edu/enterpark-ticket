package com.example.enterparkticket.apis.enduser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.example.enterparkticket"],
    exclude = [SecurityAutoConfiguration::class]
)
class EndUserApplication

fun main(args: Array<String>) {
    runApplication<EndUserApplication>(*args)
}
