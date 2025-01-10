package com.example.enterparkticket.core.domain.performance.exception

import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException

class AgeLimitException : EnterparkTicketException(PerformanceErrorCode.AGE_LIMIT) {
}
