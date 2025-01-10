package com.example.enterparkticket.core.domain.reservation.event

data class ReservationWaitingPaymentEvent(
    val reservationIds: List<Long?>,
)
