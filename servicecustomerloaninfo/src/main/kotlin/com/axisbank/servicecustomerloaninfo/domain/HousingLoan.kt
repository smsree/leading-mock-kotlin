package com.axisbank.servicecustomerloaninfo.domain

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class HousingLoan {
    private val housingLoanId: String? = null
    @NotNull
    @Positive(message = "customer mobile number should be a positive value")
    private val customerMobileNo: Long? = null
    @NotBlank(message = "housing loan needs address ")
    private val address: String? = null
    @NotBlank(message = "loan name cannot be blank value")
    private val loanName: String? = null
    @NotNull
    @Positive(message = "loan amount should be a positive value")
    private val loanamount: Int? = null
    @Min(value = 0L, message = "rating of interest should not be negative")
    private val rateOfInterest: Double? = null
    private val status: String? = null
}