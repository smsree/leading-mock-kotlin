package com.axisbank.loanofferservice.controller

import com.axisbank.loanofferservice.service.VehicleLoanService
import com.axisbank.loanofferservice.domain.VehicleLoan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/v1/vehicleLoan")
class VehicleLoanController {
    @Autowired
    private val vehicleLoanService: VehicleLoanService? = null

    @GetMapping("/")
    fun welcomeMessage(): Mono<String>? {
        return Mono.just("Welcome to axis bank vehicle loan section")
    }

    @GetMapping("/allVehicleLoan")
    fun getAllVehicleLoan(): Flux<VehicleLoan?>? {
        return vehicleLoanService!!.getAllVehicleLoan()
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewVehicleLoan(@RequestBody vehicleLoan: @Valid VehicleLoan?): Mono<VehicleLoan?>? {
        return vehicleLoanService!!.addNewLoan(vehicleLoan)
    }

    @GetMapping("/{id}")
    fun getVehicleLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<VehicleLoan?>?>? {
        return vehicleLoanService!!.getVehicleLoanById(id)
                ?.map { vehicleLoan -> ResponseEntity.ok().body(vehicleLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun getVehicleLoanByMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<VehicleLoan?>?>? {
        return vehicleLoanService!!.getVehicleLoanByMobileNo(mobileNo)
                ?.map { vehicleLoan -> ResponseEntity.ok().body(vehicleLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @PutMapping("/{id}")
    fun updateVehicleLoanById(@PathVariable("id") id: String?, @RequestBody vehicleLoan: VehicleLoan?): Mono<ResponseEntity<VehicleLoan?>?>? {
        return vehicleLoanService!!.updateVehcileLoanById(id, vehicleLoan!!)
                ?.map { loan -> ResponseEntity.ok().body(loan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExistingLoanById(@PathVariable("id") id: String?): Mono<Void?>? {
        return vehicleLoanService!!.deleteVehicleLoanById(id)
    }
}