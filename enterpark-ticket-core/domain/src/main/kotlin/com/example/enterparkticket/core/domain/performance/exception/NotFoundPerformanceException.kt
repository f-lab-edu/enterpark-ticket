package com.example.enterparkticket.core.domain.performance.exception

import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException

class NotFoundPerformanceException :
    EnterparkTicketException(PerformanceErrorCode.PERFORMANCE_NOT_FOUND) {
}
