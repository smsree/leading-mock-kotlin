package com.axisbank.servicecustomerloaninfo.controller

import com.axisbank.servicecustomerloaninfo.client.*
import com.axisbank.servicecustomerloaninfo.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/clientInfo")
class ClientInfoController {
    @Autowired
    var businessLoanWebClient: BusinessLoanWebClient? = null

    @Autowired
    lateinit  var customerWebClient: CustomerWebClient

    @Autowired
    var educationalLoanWebclient: EducationalLoanWebclient? = null

    @Autowired
    var housingLoanWebClient: HousingLoanWebClient? = null

    @Autowired
    var vehicleLoanWebClient: VehicleLoanWebClient? = null
    @GetMapping("/{customerMobileNo}")
    fun retriveAllInfoAboutACustomerViaMobileNo(@PathVariable("customerMobileNo") mobileNo: Long): Mono<ClientInfoLoans> {
        val customer: Customer = customerWebClient.retriveCustomerInfoByMobileNo(mobileNo)

        val businessLoan: BusinessLoan = businessLoanWebClient!!.retriveBusinessLoanUsingMobileNo(mobileNo)!!.block()!!
        val educationalLoan: EducationalLoan = educationalLoanWebclient!!.retriveEducationalLoanDetailsViaMobileNo(mobileNo)!!.block()!!
        val housingLoan: HousingLoan = housingLoanWebClient!!.returiveHousingLoanUsingMobileNo(mobileNo)!!.block()!!
        val vehicalLoan: VehicleLoan = vehicleLoanWebClient!!.retriveVehicleloanUsingMobileNo(mobileNo)!!.block()!!
        println(customer.toString())

        return Mono.just<ClientInfoLoans?>(ClientInfoLoans(customer, businessLoan, educationalLoan, housingLoan, vehicalLoan))
    }
}