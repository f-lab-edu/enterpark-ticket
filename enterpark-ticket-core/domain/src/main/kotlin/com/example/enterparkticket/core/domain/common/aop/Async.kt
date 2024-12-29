package com.example.enterparkticket.core.domain.common.aop

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class Async(advice: AsyncAdvice) {

    init {
        asyncAdvice = advice
    }

    companion object {
        private lateinit var asyncAdvice: AsyncAdvice

        fun <T> eventTaskExecutor(function: () -> T): T {
            return asyncAdvice.eventTaskExecutor(function)
        }
    }

    @Component
    class AsyncAdvice {

        @Async("eventTaskExecutor")
        fun <T> eventTaskExecutor(function: () -> T): T {
            return function.invoke()
        }
    }
}
