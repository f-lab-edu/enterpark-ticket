package com.example.enterparkticket.core.domain.common.consts

object EnterparkTicketConsts {

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val CONFLICT = 409
    const val INTERNAL_SERVER_ERROR = 500

    const val KAKAO_TOKEN_PREFIX = "kakaoToken:"
    const val BEARER = "Bearer "
    const val KAKAO_AK = "KakaoAK "

    const val PRIMARY_TRANSACTION_MANAGER = "primaryTransactionManager"
    const val BATCH_TRANSACTION_MANAGER = "batchTransactionManager"
}