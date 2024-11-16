package com.example.enterparkticket.apis.enduser.config.exception.dto

import com.example.enterparkticket.core.domain.common.exeption.ErrorDescription

data class ErrorResponse(
    val code: String,
    val message: Any,
) {

    companion object {

        fun of(errorDescription: ErrorDescription): ErrorResponse {
            return ErrorResponse(errorDescription.code, errorDescription.message)
        }
    }
}
