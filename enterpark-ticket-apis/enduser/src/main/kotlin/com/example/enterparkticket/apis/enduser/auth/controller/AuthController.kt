package com.example.enterparkticket.apis.enduser.auth.controller

import com.example.enterparkticket.apis.enduser.auth.dto.request.RegisterUserRequest
import com.example.enterparkticket.apis.enduser.auth.dto.response.RegisterUserResponse
import com.example.enterparkticket.apis.enduser.auth.service.RegisterUseCase
import com.example.enterparkticket.apis.enduser.auth.service.WithdrawUseCase
import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthDetails
import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthUser
import com.example.enterparkticket.apis.enduser.config.jwt.dto.toUserId
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val registerUseCase: RegisterUseCase,
    private val withdrawUseCase: WithdrawUseCase,
) {

    @PostMapping("/oauth/kakao/register")
    fun kakaoOAuthRegisterUser(
        @RequestParam("code") code: String,
        @Valid @RequestBody request: RegisterUserRequest,
    ): ResponseEntity<RegisterUserResponse> {
        val response = registerUseCase.registerUser(code, request)
        return ResponseEntity.status(CREATED).body(response)
    }

    @PatchMapping("/oauth/kakao/withdrawal")
    fun kakaoOAuthWithdrawUser(@AuthUser user: AuthDetails): ResponseEntity<String> {
        withdrawUseCase.withdrawUser(user.toUserId())
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.")
    }
}
