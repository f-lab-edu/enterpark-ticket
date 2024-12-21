package com.example.enterparkticket.core.domain.reservation.repository

import com.example.enterparkticket.core.domain.reservation.domain.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long> {
}
