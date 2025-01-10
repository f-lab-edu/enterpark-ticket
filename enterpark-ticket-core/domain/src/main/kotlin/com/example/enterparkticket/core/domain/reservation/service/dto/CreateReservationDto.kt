package com.example.enterparkticket.core.domain.reservation.service.dto

import com.example.enterparkticket.core.domain.reservation.domain.Reservation
import com.example.enterparkticket.core.domain.reservation.domain.StateType
import com.example.enterparkticket.core.domain.reservation.domain.TicketReceiptType

data class CreateReservationDto(
    val performanceId: Long,
    val ticketReceiptType: TicketReceiptType,
    val seats: List<CreateReservationSeatDto>,
) {

    fun toReservationEntity(userId: Long, seatId: Long?): Reservation {
        return Reservation(
            StateType.WAITING_PAYMENT,
            ticketReceiptType,
            userId,
            performanceId,
            seatId,
        )
    }
}

data class CreateReservationSeatDto(
    val seatNumber: String,
)
