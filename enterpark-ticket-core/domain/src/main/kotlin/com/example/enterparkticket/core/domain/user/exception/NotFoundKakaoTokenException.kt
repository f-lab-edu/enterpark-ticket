package com.example.enterparkticket.core.domain.user.exception

import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException

class NotFoundKakaoTokenException : EnterparkTicketException(UserErrorCode.KAKAO_TOKEN_NOT_FOUND) {
}
