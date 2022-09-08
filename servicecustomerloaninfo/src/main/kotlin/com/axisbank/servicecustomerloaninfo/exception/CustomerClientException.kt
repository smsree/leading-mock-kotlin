package com.axisbank.servicecustomerloaninfo.exception

class CustomerClientException(override var message: String, var statusCode: Int) : RuntimeException(message){


    fun getStatusCode(): Int? {
        return statusCode
    }

    fun setStatusCode(statusCode: Int?) {
        this.statusCode = statusCode!!
    }
}