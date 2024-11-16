package com.example.enterparkticket.core.domain.user.exception

import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException

class AlreadyRegisterUserException : EnterparkTicketException(UserErrorCode.USER_ALREADY_REGISTER) {
}