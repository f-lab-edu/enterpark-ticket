package com.example.enterparkticket.core.domain.common.exeption

data class ErrorDescription(
    val code: String,
    val message: String,
    val status: Int,
)
