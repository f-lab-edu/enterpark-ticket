package com.example.enterparkticket.core.domain.reservation.config

import com.example.enterparkticket.core.domain.DomainSpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest(
    classes = [DomainSpringBootApplication::class],
    properties = ["spring.profiles.active=test"],
)
@ContextConfiguration(classes = [JpaTestConfig::class])
annotation class DomainSpringBootTest
