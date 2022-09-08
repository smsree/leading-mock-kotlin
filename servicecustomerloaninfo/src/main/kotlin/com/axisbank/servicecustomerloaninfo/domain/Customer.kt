package com.axisbank.servicecustomerloaninfo.domain

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class Customer {
    private val customerId: String? = null
    @NotBlank(message = "customer name must not be null")
    private val name: String? = null
    @NotBlank(message = "customer work should not be null if student please mention student in place of work")
    private val work: String? = null
    @NotNull
    @Positive(message = "adhar number must be a positive value")
    private val adharNo: Long? = null
    private val panNo: String? = null
    @NotNull
    @Positive(message = "phone number is needed to create an account in your name it should be a positive value")
    private val mobileNo: Long? = null
    private val dob: String? = null

    override fun toString(): String {
        return "Customer(customerId=$customerId, name=$name, work=$work, adharNo=$adharNo, panNo=$panNo, mobileNo=$mobileNo, dob=$dob)"
    }



}