package com.example.enterparkticket.apis.enduser.config.exception

import com.example.enterparkticket.apis.enduser.config.exception.dto.ErrorResponse
import com.example.enterparkticket.core.domain.common.exeption.EnterparkTicketException
import com.example.enterparkticket.core.domain.common.exeption.GlobalErrorCode.SERVER_ERROR
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

private val kLogger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EnterparkTicketException::class)
    fun handleEnterparkTicketException(
        ex: EnterparkTicketException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ex.getErrorDescription())
        return ResponseEntity.status(ex.getErrorDescription().status).body(response)
    }

    /**
     * 400 Bad Request
     */
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val message = ex.bindingResult.fieldErrors.map {
            mapOf(it.field to it.defaultMessage)
        }
        val response = ErrorResponse(status.toString().split(" ")[1], message)

        return ResponseEntity.status(BAD_REQUEST).body(response)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        ex: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val message = mutableListOf<Map<String?, String>>()
        ex.constraintViolations.forEach {
            val propertyPaths = it.propertyPath.toString().split(".")
            val property = propertyPaths.lastOrNull()
            message.add(mapOf(property to it.message))
        }
        val response = ErrorResponse(BAD_REQUEST.name, message)

        return ResponseEntity.status(BAD_REQUEST).body(response)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val message = ex.mostSpecificCause.message.toString()
        val response = ErrorResponse(status.toString().split(" ")[1], message)

        return ResponseEntity.status(BAD_REQUEST).body(response)
    }

    /**
     * 500 Internal Server Error
     */
    @ExceptionHandler(Exception::class)
    protected fun handleException(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        kLogger.error { ex.message }
        val errorDescription = SERVER_ERROR.getErrorDescription()
        val response = ErrorResponse(errorDescription.code, errorDescription.message)
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response)
    }
}
