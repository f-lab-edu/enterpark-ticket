package com.example.enterparkticket.apis.enduser.reservation.dto.request

import com.example.enterparkticket.core.domain.reservation.domain.TicketReceiptType
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationDto
import com.example.enterparkticket.core.domain.reservation.service.dto.CreateReservationSeatDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateReservationRequest(

    @field:NotNull(message = "공연 아이디는 필수 입력입니다.")
    val performanceId: Long,

    @field:NotNull(message = "티켓 수령 방법은 필수 입력입니다.")
    val ticketReceiptType: TicketReceiptType,

    @field:Size(min = 1, max = 10, message = "좌석은 1~10개만 가능합니다.")
    @field:Valid
    val seats: List<CreateReservationSeatRequest>,
) {

    fun toCreateReservationDto(): CreateReservationDto {
        return CreateReservationDto(
            performanceId,
            ticketReceiptType,
            seats.map { CreateReservationSeatDto(it.seatNumber) }
        )
    }
}

data class CreateReservationSeatRequest(

    @field:NotBlank(message = "좌석 번호는 필수 입력입니다.")
    val seatNumber: String,
)
