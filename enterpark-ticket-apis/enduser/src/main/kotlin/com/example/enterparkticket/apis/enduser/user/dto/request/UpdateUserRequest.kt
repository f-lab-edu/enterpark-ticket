package com.example.enterparkticket.apis.enduser.user.dto.request

import com.example.enterparkticket.core.domain.user.service.dto.UpdateUserDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UpdateUserRequest(

    @field:NotBlank(message = "이름은 필수 입력입니다.")
    @field:Size(min = 1, max = 20, message = "이름은 1~20자만 가능합니다.")
    val name: String,

    @field:NotBlank(message = "전화번호는 필수 입력입니다.")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 양식에 맞지 않습니다.")
    val phoneNumber: String,

    @field:Size(max = 255, message = "배송지는 0~255자만 가능합니다.")
    val address: String?,
) {

    fun toUpdateUserDto(email: String): UpdateUserDto {
        return UpdateUserDto(
            name,
            email,
            phoneNumber,
            address,
        )
    }
}
