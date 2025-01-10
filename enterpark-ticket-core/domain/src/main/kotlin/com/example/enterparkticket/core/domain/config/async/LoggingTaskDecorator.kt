package com.example.enterparkticket.core.domain.config.async

import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class LoggingTaskDecorator : TaskDecorator {

    override fun decorate(task: Runnable): Runnable {
        val callerThreadContext = MDC.getCopyOfContextMap()

        return Runnable {
            callerThreadContext?.let {
                MDC.setContextMap(it)
            }
            task.run()
        }
    }
}
