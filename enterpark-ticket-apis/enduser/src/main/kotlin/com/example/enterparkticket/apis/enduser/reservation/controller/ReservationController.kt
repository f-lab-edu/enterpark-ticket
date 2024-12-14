package com.example.enterparkticket.apis.enduser.reservation.controller

import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthDetails
import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthUser
import com.example.enterparkticket.apis.enduser.config.jwt.dto.toUserId
import com.example.enterparkticket.apis.enduser.reservation.dto.request.CreateReservationRequest
import com.example.enterparkticket.core.domain.reservation.service.ReservationDomainService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/reservations")
class ReservationController(private val reservationDomainService: ReservationDomainService) {

    @PostMapping
    fun createReservation(
        @AuthUser user: AuthDetails,
        @Valid @RequestBody request: CreateReservationRequest,
    ): ResponseEntity<String> {
        reservationDomainService.createReservation(
            user.toUserId(),
            request.toCreateReservationDto()
        )
        return ResponseEntity.status(CREATED).body("예매가 완료되었습니다.")
    }
}
