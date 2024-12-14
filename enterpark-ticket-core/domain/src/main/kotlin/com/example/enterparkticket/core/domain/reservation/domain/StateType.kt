package com.example.enterparkticket.core.domain.reservation.domain

enum class StateType(val value: String) {
    WAITING_PAYMENT("결제 대기"),
    RESERVED("예매 완료"),
    CANCELED("예매 취소"),
}
