package com.axisbank.loanofferservice.controller

import com.axisbank.loanofferservice.domain.BusinessLoan
import com.axisbank.loanofferservice.service.BusinessLoanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/businessLoan")
class BusinessLoanController {
    @Autowired
    private val businessLoanService: BusinessLoanService? = null

    @GetMapping("/")
    fun welcomeMessage(): Mono<String>? {
        return Mono.just("Welcome to the axis bank business loan section")
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewLoan(@RequestBody businessLoan: BusinessLoan?): Mono<BusinessLoan?>? {
        return businessLoanService!!.addNewBusinessLoan(businessLoan)
    }

    @GetMapping("/allBuisnessLoan")
    fun getAllBusinessLoan(): Flux<BusinessLoan?>? {
        return businessLoanService!!.getAllBusinessLoanAvailable()
    }

    @GetMapping("/{id}")
    fun getBusinessLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<BusinessLoan?>?>? {
        return businessLoanService!!.findBusinessLoanById(id)
                ?.map { businessLoan -> ResponseEntity.ok().body(businessLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun getBusinessLoanByCustomerMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<BusinessLoan?>?>? {
        return businessLoanService!!.findBusinessLoanByMobileNo(mobileNo)
                ?.map { businessLoan -> ResponseEntity.ok().body(businessLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @PutMapping("/{id}")
    fun updateExistingBusinessLoanById(@PathVariable("id") id: String?, @RequestBody updatedBusinessLoan: BusinessLoan?): Mono<ResponseEntity<BusinessLoan?>?>? {
        return businessLoanService!!.updateExistingBusinessLoanByIdService(id, updatedBusinessLoan!!)
                ?.map { businessLoan -> ResponseEntity.ok().body(businessLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBusinessLoanById(@PathVariable("id") id: String?): Mono<Void?>? {
        return businessLoanService!!.deleteBusinessLoanByIdService(id)
    }
}