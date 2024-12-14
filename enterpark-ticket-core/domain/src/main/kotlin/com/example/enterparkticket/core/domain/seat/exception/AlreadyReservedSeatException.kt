package com.example.enterparkticket.core.domain.seat.exception

import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException

class AlreadyReservedSeatException :
    EnterparkTicketException(ReservationErrorCode.SEAT_ALREADY_RESERVED) {
}
