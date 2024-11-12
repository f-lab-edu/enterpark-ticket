package com.example.enterparkticket.apis.enduser.auth.dto.request

import com.example.enterparkticket.core.domain.user.service.dto.RegisterUserDto
import com.example.enterparkticket.core.domain.user.domain.GenderType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class RegisterUserRequest(

    @field:NotBlank(message = "이름은 필수 입력입니다.")
    @field:Size(min = 1, max = 20, message = "이름은 1~20자만 가능합니다.")
    val name: String,

    @field:NotBlank(message = "전화번호는 필수 입력입니다.")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 양식에 맞지 않습니다.")
    val phoneNumber: String,

    @field:NotNull(message = "생년월일은 필수 입력입니다.")
    val birthDate: LocalDate,

    @field:NotNull(message = "성별은 필수 입력입니다.")
    val gender: GenderType,
) {

    fun toRegisterUserDto(): RegisterUserDto {
        return RegisterUserDto(
            name,
            phoneNumber,
            birthDate,
            gender
        )
    }
}
