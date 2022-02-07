package com.example.millionpubs.errors

import com.google.gson.JsonSyntaxException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handleNBPExchangeRateException(e: NBPExchangeRateException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(
            e,
            e.toString(),
            HttpHeaders.EMPTY,
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    @ExceptionHandler
    fun handleNumberFormatException(e: NumberFormatException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(
            e,
            e.toString(),
            HttpHeaders.EMPTY,
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    @ExceptionHandler
    fun handleJsonSyntaxException(e: JsonSyntaxException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(
            e,
            e.toString(),
            HttpHeaders.EMPTY,
            HttpStatus.BAD_REQUEST,
            request
        )
    }
}