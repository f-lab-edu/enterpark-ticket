package com.example.enterparkticket.core.domain.performance.domain

import com.example.enterparkticket.core.domain.performance.exception.AgeLimitException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import java.time.LocalDate

class PerformanceTest : FunSpec({

    context("validateUserAge") {
        val birthDateAll = LocalDate.now()
        val birthDate12 = LocalDate.of(birthDateAll.year - 12, 1, 1)
        val birthDate15 = LocalDate.of(birthDateAll.year - 15, 1, 1)
        val birthDate19 = LocalDate.of(birthDateAll.year - 19, 1, 1)

        test("공연 나이 제한이 전체 관람가일 경우 모든 회원이 예매 가능") {
            val performance = Performance(
                "알라딘",
                "이미지",
                "설명",
                LocalDate.of(2024, 12, 19),
                LocalDate.of(2025, 1, 19),
                120,
                AgeLimitType.ALL,
                1L
            )
            performance.validateUserAge(birthDateAll)
            performance.validateUserAge(birthDate12)
            performance.validateUserAge(birthDate15)
            performance.validateUserAge(birthDate19)
        }

        test("공연 나이 제한이 12세 이상일 경우 12세 이상인 회원만 예매 가능") {
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
            shouldThrow<AgeLimitException> {
                performance.validateUserAge(birthDateAll)
            }
            performance.validateUserAge(birthDate12)
            performance.validateUserAge(birthDate15)
            performance.validateUserAge(birthDate19)
        }

        test("공연 나이 제한이 15세 이상일 경우 15세 이상인 회원만 예매 가능") {
            val performance = Performance(
                "알라딘",
                "이미지",
                "설명",
                LocalDate.of(2024, 12, 19),
                LocalDate.of(2025, 1, 19),
                120,
                AgeLimitType.AGE_15,
                1L
            )
            shouldThrow<AgeLimitException> {
                performance.validateUserAge(birthDateAll)
            }
            shouldThrow<AgeLimitException> {
                performance.validateUserAge(birthDate12)
            }
            performance.validateUserAge(birthDate15)
            performance.validateUserAge(birthDate19)
        }

        test("공연 나이 제한이 19세 이상일 경우 19세 이상인 회원만 예매 가능") {
            val performance = Performance(
                "알라딘",
                "이미지",
                "설명",
                LocalDate.of(2024, 12, 19),
                LocalDate.of(2025, 1, 19),
                120,
                AgeLimitType.AGE_19,
                1L
            )
            shouldThrow<AgeLimitException> {
                performance.validateUserAge(birthDateAll)
            }
            shouldThrow<AgeLimitException> {
                performance.validateUserAge(birthDate12)
            }
            shouldThrow<AgeLimitException> {
                performance.validateUserAge(birthDate15)
            }
            performance.validateUserAge(birthDate19)
        }
    }
})
