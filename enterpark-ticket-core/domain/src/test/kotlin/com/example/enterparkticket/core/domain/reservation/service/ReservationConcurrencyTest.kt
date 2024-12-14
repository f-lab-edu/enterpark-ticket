package com.example.enterparkticket.core.domain.reservation.service

import com.example.enterparkticket.core.domain.reservation.config.DomainSpringBootTest
import com.example.enterparkticket.core.domain.reservation.config.RedisExtension
import com.example.enterparkticket.core.domain.reservation.domain.TicketReceiptType
import com.example.enterparkticket.core.domain.reservation.repository.ReservationRepository
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationDto
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationSeatDto
import com.example.enterparkticket.core.domain.seat.domain.Place
import com.example.enterparkticket.core.domain.seat.domain.Seat
import com.example.enterparkticket.core.domain.seat.repository.PlaceRepository
import com.example.enterparkticket.core.domain.seat.repository.SeatRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.extensions.install
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.testcontainers.perSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

private val logger = KotlinLogging.logger {}

@DomainSpringBootTest
class ReservationConcurrencyTest @Autowired constructor(
    private val placeRepository: PlaceRepository,
    private val seatRepository: SeatRepository,
    private val reservationRepository: ReservationRepository,
) : BehaviorSpec({

    val redis = install(RedisExtension())
    listeners(redis.perSpec())

    val nThreads = 1000
    lateinit var executorService: ExecutorService
    lateinit var countDownLatch: CountDownLatch
    lateinit var reservationDomainService: ReservationDomainService

    beforeTest {
        executorService = Executors.newFixedThreadPool(nThreads)
        countDownLatch = CountDownLatch(nThreads)
        reservationDomainService = ReservationDomainService(seatRepository, reservationRepository)
    }

    Given("동시성 티켓 예매") {
        val place = Place("고척스카이돔", "서울특별시 구로구 경인로 430")
        placeRepository.save(place)
        val seats = listOf("A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "I1", "J1", "K1").map {
            Seat("S", it, 100000, 100L, place)
        }
        seatRepository.saveAll(seats)

        When("1000개의 요청이 동시에 실행되면") {
            val userId = 1L
            val successCounter = AtomicInteger()
            val seatsDto = listOf(CreateReservationSeatDto("A1"), CreateReservationSeatDto("K1"))
            val dto = CreateReservationDto(100L, TicketReceiptType.DELIVERY, seatsDto)
            repeat(nThreads) {
                executorService.submit {
                    try {
                        reservationDomainService.createReservation(userId, dto)
                        successCounter.getAndIncrement()
                    } catch (e: Exception) {
                        logger.error { e.message }
                    } finally {
                        countDownLatch.countDown()
                    }
                }
            }
            countDownLatch.await()

            Then("1개의 요청만 성공해야 한다") {
                successCounter.get() shouldBe 1
            }
        }
    }
})
