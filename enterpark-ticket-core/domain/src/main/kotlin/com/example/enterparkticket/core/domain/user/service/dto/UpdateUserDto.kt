package com.example.enterparkticket.core.domain.user.service.dto

data class UpdateUserDto(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val address: String?,
)