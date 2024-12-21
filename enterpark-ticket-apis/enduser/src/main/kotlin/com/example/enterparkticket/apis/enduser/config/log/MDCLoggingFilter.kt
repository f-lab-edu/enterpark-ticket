package com.example.enterparkticket.apis.enduser.config.log

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MDCLoggingFilter : Filter {

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        filterChain: FilterChain
    ) {
        val uuid = UUID.randomUUID()
        MDC.put(REQUEST_ID, uuid.toString())
        filterChain.doFilter(request, response)
        MDC.clear()
    }

    companion object {
        const val REQUEST_ID = "request_id"
    }
}
