package com.example.enterparkticket.core.domain.reservation.service

import com.example.enterparkticket.core.domain.common.aop.lock
import com.example.enterparkticket.core.domain.performance.service.PerformanceDomainService
import com.example.enterparkticket.core.domain.reservation.event.ReservationWaitingPaymentEvent
import com.example.enterparkticket.core.domain.reservation.repository.ReservationRepository
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationDto
import com.example.enterparkticket.core.domain.seat.repository.SeatRepository
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ReservationDomainService(
    private val userDomainService: UserDomainService,
    private val performanceDomainService: PerformanceDomainService,
    private val seatRepository: SeatRepository,
    private val reservationRepository: ReservationRepository,
    private val publisher: ApplicationEventPublisher,
) {

    fun createReservation(userId: Long, dto: CreateReservationDto) {
        val user = userDomainService.findUserById(userId)
        val performanceId = dto.performanceId
        val performance = performanceDomainService.findPerformanceById(performanceId)
        performance.validateUserAge(user.birthDate)

        lock(SEAT_LOCK_KEY, "$performanceId", "${dto.seats}") {
            val seatNumbers = dto.seats.map { it.seatNumber }
            val seats = seatRepository
                .findByGradeSeatPerformanceIdAndSeatNumberIn(performanceId, seatNumbers)
                .onEach {
                    it.validateIsReserved()
                }

            seatRepository.bulkUpdateReserveSeats(performanceId, seatNumbers)

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
