package com.example.enterparkticket.core.domain.seat.repository

import com.example.enterparkticket.core.domain.seat.domain.GradeSeat
import org.springframework.data.jpa.repository.JpaRepository

interface GradeSeatRepository : JpaRepository<GradeSeat, Long> {
}
