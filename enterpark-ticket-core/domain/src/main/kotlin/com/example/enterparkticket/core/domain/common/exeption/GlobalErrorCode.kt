package com.example.enterparkticket.core.domain.common.exeption

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.INTERNAL_SERVER_ERROR
import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.UNAUTHORIZED

enum class GlobalErrorCode(private val status: Int, private val message: String) : BaseErrorCode {

    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_EMAIL(UNAUTHORIZED, "유효하지 않은 이메일 주소입니다."),

    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 오류가 발생하였습니다."),
    NOT_AVAILABLE_DISTRIBUTED_LOCK(INTERNAL_SERVER_ERROR, "DistributedLock is not available");

    override fun getErrorDescription(): ErrorDescription {
        return ErrorDescription(name, message, status)
    }
}