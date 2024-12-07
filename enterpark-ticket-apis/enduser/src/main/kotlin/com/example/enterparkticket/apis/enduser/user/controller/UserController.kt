package com.example.enterparkticket.apis.enduser.user.controller

import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthDetails
import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthUser
import com.example.enterparkticket.apis.enduser.config.jwt.dto.toUserId
import com.example.enterparkticket.apis.enduser.user.dto.request.UpdateUserRequest
import com.example.enterparkticket.apis.enduser.user.service.UpdateUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/users")
class UserController(private val updateUseCase: UpdateUseCase) {

    @PatchMapping("/update")
    fun updateUser(
        @AuthUser user: AuthDetails,
        @Valid @RequestBody request: UpdateUserRequest,
    ): ResponseEntity<String> {
        updateUseCase.updateUser(user.toUserId(), request)
        return ResponseEntity.ok("회원 정보 수정이 완료되었습니다.")
    }
}
