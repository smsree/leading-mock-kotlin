package com.axisbank.loanapp.controller

import com.axisbank.loanapp.domain.Customer
import com.axisbank.loanapp.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function


@RestController
@RequestMapping("/v1/customer")
class CustomerController {
    @Autowired
    private val customerService: CustomerService? = null

    @get:GetMapping("/")
    val welcomeMsg: Mono<String>
        get() = Mono.just("Welcome to Axis bank")

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewCustomer(@RequestBody customer: Customer?): Mono<Customer> {
        return customerService!!.addNewCutomer(customer!!)
    }

    @get:GetMapping("/all")
    val allCustomer: Flux<Customer?>
        get() = customerService!!.allCustomer

    @GetMapping("/{id}")
    fun getByCustomerId(@PathVariable("id") customerId: String?): Mono<Customer?> {
        return customerService!!.getCustomerById(customerId!!)
    }

    @PutMapping("/{id}")
    fun updateExistingCustomer(@PathVariable("id") customerId: String?, @RequestBody customer: Customer?): Mono<ResponseEntity<Customer?>>? {
        return customerService!!.updateCustomerById(customerId!!, customer!!)
                ?.map<ResponseEntity<Customer?>>(Function { customer1: Customer? -> ResponseEntity.ok().body(customer1) })
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/mobile/{mobileNo}")
    fun getCustomerInfoByMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<Customer?>>? {
        return customerService!!.getCustomerByMobileNo(mobileNo)
                ?.map<ResponseEntity<Customer?>>(Function { customer: Customer? -> ResponseEntity.ok().body(customer) })
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomerById(@PathVariable("id") id: String?): Mono<Void> {
        return customerService!!.deleteCustomerById(id!!)
    }
}