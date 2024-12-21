package com.example.enterparkticket.core.domain.performance.exception

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.BAD_REQUEST
import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.NOT_FOUND
import com.example.enterparkticket.core.domain.common.exeption.BaseErrorCode
import com.example.enterparkticket.core.domain.common.exeption.ErrorDescription

enum class PerformanceErrorCode(private val status: Int, private val message: String) :
    BaseErrorCode {

    PERFORMANCE_NOT_FOUND(NOT_FOUND, "존재하지 않는 공연입니다."),
    AGE_LIMIT(BAD_REQUEST, "공연 관람 가능 나이가 아닙니다.");

    override fun getErrorDescription(): ErrorDescription {
        return ErrorDescription(name, message, status)
    }
}
