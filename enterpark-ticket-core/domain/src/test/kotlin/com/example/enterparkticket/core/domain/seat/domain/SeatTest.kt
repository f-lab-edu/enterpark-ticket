package com.example.enterparkticket.core.domain.seat.domain

import com.example.enterparkticket.core.domain.performance.domain.AgeLimitType
import com.example.enterparkticket.core.domain.performance.domain.Performance
import com.example.enterparkticket.core.domain.seat.exception.AlreadyReservedSeatException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import java.time.LocalDate

class SeatTest : FunSpec({

    context("validateIsReserved") {
        val performance = Performance(
            "알라딘",
            "이미지",
            "설명",
            LocalDate.of(2024, 12, 19),
            LocalDate.of(2025, 1, 19),
            120,
            AgeLimitType.AGE_12,
            1L
        )
        val gradeSeat = GradeSeat(GradeType.S, 50, 100000, performance)

        test("이미 예매된 좌석이라면 예외가 발생한다") {
            val seat = Seat("A1", gradeSeat, true)
            shouldThrow<AlreadyReservedSeatException> {
                seat.validateIsReserved()
            }
        }

        test("아직 예매되지 않은 좌석이면 예외가 발생하지 않는다") {
            val seat = Seat("A1", gradeSeat, false)
            seat.validateIsReserved()
        }
    }
})
