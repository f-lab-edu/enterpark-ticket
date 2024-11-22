package com.example.enterparkticket.core.domain.common.exeption

abstract class EnterparkTicketException(private val errorCode: BaseErrorCode) : RuntimeException() {

    fun getErrorDescription(): ErrorDescription {
        return errorCode.getErrorDescription()
    }
}
