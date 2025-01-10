package com.example.enterparkticket.apis.enduser.reservation.service

import com.example.enterparkticket.apis.enduser.reservation.dto.request.CreateReservationRequest
import com.example.enterparkticket.core.domain.performance.service.PerformanceDomainService
import com.example.enterparkticket.core.domain.reservation.service.ReservationDomainService
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class CreateUseCase(
    private val userDomainService: UserDomainService,
    private val performanceDomainService: PerformanceDomainService,
    private val reservationDomainService: ReservationDomainService,
) {

    @PreAuthorize("hasRole('USER')")
    fun createReservation(userId: Long, request: CreateReservationRequest) {
        val user = userDomainService.findUserById(userId)
        val performance = performanceDomainService.findPerformanceById(request.performanceId)
        performance.validateUserAge(user.birthDate)
        reservationDomainService.createReservation(userId, request.toCreateReservationDto())
    }
}
