package com.example.enterparkticket.core.domain.reservation.service

import com.example.enterparkticket.core.domain.common.aop.lock
import com.example.enterparkticket.core.domain.reservation.event.ReservationWaitingPaymentEvent
import com.example.enterparkticket.core.domain.reservation.repository.ReservationRepository
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationDto
import com.example.enterparkticket.core.domain.seat.repository.SeatRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ReservationDomainService(
    private val seatRepository: SeatRepository,
    private val reservationRepository: ReservationRepository,
    private val publisher: ApplicationEventPublisher,
) {

    fun createReservation(userId: Long, dto: CreateReservationDto) {
        lock(SEAT_LOCK_KEY, "${dto.performanceId}", "${dto.seats}") {
            val seatNumbers = dto.seats.map { it.seatNumber }
            val seats = seatRepository
                .findByGradeSeatPerformanceIdAndSeatNumberIn(dto.performanceId, seatNumbers)
                .onEach {
                    it.validateIsReserved()
                }

            seatRepository.bulkUpdateReserveSeats(dto.performanceId, seatNumbers)

            val reservations = seats.map { dto.toReservationEntity(userId, it.id) }
            val reservationIds = reservations.map { it.id }
            reservationRepository.saveAll(reservations)
            publisher.publishEvent(ReservationWaitingPaymentEvent(reservationIds))
        }
    }

    companion object {
        const val SEAT_LOCK_KEY = "SEAT_LOCK"
    }
}
