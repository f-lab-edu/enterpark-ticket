package com.example.enterparkticket.core.domain.config.async

import com.example.enterparkticket.core.domain.config.EnterparkTicketConfig
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.ThreadPoolExecutor

@EnableAsync
class AsyncConfig : EnterparkTicketConfig {

    @Bean
    fun eventTaskExecutor(): ThreadPoolTaskExecutor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 10
            queueCapacity = 50
            maxPoolSize = 30
            setThreadNamePrefix("event-async-")
            setTaskDecorator(LoggingTaskDecorator())
            setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        }
    }
}
