package com.example.enterparkticket.core.domain.user.exception

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.CONFLICT
import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.NOT_FOUND
import com.example.enterparkticket.core.domain.common.exeption.BaseErrorCode
import com.example.enterparkticket.core.domain.common.exeption.ErrorDescription

enum class UserErrorCode(private val status: Int, private val message: String) : BaseErrorCode {

    USER_ALREADY_REGISTER(CONFLICT, "이미 존재하는 회원입니다."),
    USER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다.");

    override fun getErrorDescription(): ErrorDescription {
        return ErrorDescription(name, message, status)
    }
}
