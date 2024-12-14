package com.example.enterparkticket.core.domain.seat.exception

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.CONFLICT
import com.example.enterparkticket.core.domain.common.exeption.BaseErrorCode
import com.example.enterparkticket.core.domain.common.exeption.ErrorDescription

enum class ReservationErrorCode(
    private val status: Int,
    private val message: String
) : BaseErrorCode {

    SEAT_ALREADY_RESERVED(CONFLICT, "이미 예매된 좌석입니다.");

    override fun getErrorDescription(): ErrorDescription {
        return ErrorDescription(name, message, status)
    }
}
