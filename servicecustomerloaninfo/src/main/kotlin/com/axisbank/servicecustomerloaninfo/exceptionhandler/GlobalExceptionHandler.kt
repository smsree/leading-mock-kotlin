package com.axisbank.servicecustomerloaninfo.exceptionhandler

import com.axisbank.servicecustomerloaninfo.exception.CustomerClientException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomerClientException::class)
    fun customerClientException4XX(ex: CustomerClientException): ResponseEntity<String?>? {
        println("Exception caught customerClientException4XX:{}"+ ex.message)
        return ResponseEntity.status(ex.getStatusCode()!!).body(ex.message)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleClientServerException(ex: RuntimeException): ResponseEntity<String?>? {
        println("Exception handle in server exception:{}"+ ex.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.message)
    }
}