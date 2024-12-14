package com.example.enterparkticket.core.domain.seat.repository

import com.example.enterparkticket.core.domain.seat.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SeatRepository : JpaRepository<Seat, Long> {

    fun findByPerformanceIdAndSeatNumberIn(
        performanceId: Long,
        seatNumbers: List<String>
    ): List<Seat>

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Seat s SET s.isReserved = true WHERE s.performanceId = :performanceId AND s.seatNumber IN :seatNumbers")
    fun bulkUpdateReserveSeats(performanceId: Long, seatNumbers: List<String>)
}
