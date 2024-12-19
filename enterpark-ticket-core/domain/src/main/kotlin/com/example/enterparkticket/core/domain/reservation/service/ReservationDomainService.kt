package com.example.enterparkticket.core.domain.reservation.service

import com.example.enterparkticket.core.domain.common.aop.lock
import com.example.enterparkticket.core.domain.reservation.repository.ReservationRepository
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationDto
import com.example.enterparkticket.core.domain.seat.repository.SeatRepository
import org.springframework.stereotype.Service

@Service
class ReservationDomainService(
    private val seatRepository: SeatRepository,
    private val reservationRepository: ReservationRepository,
) {

    fun createReservation(userId: Long, dto: CreateReservationDto) {
        lock(SEAT_LOCK_KEY, "${dto.performanceId}", "${dto.seats}") {
            val seatNumbers = dto.seats.map { it.seatNumber }
            val seats = seatRepository
                .findByPerformanceIdAndSeatNumberIn(dto.performanceId, seatNumbers)
                .onEach {
                    it.validateIsReserved()
                }

            seatRepository.bulkUpdateReserveSeats(dto.performanceId, seatNumbers)

            val reservations = seats.map { dto.toReservationEntity(userId, it.id) }
            reservationRepository.saveAll(reservations)
        }
    }

    companion object {
        const val SEAT_LOCK_KEY = "SEAT_LOCK"
    }
}
