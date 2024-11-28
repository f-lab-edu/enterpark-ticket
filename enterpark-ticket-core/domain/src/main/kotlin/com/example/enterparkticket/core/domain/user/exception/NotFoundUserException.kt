package com.example.enterparkticket.core.domain.user.exception

import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException

class NotFoundUserException : EnterparkTicketException(UserErrorCode.USER_NOT_FOUND) {
}
