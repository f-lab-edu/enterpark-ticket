package com.example.enterparkticket.core.domain.payment.event

import com.example.enterparkticket.core.domain.common.aop.Transaction
import com.example.enterparkticket.core.domain.reservation.event.ReservationWaitingPaymentEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

private val logger = KotlinLogging.logger {}

@Component
class ReservationWaitingPaymentEventHandler {

    @Async
    @TransactionalEventListener
    fun handle(event: ReservationWaitingPaymentEvent) = Transaction.propagationRequiresNew {
        logger.info { "티켓 예매 결제 하기 ${event.reservationIds}" }
    }
}
