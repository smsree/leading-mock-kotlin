package com.axisbank.loanofferservice.controller
import com.axisbank.loanofferservice.domain.EducationalLoan
import com.axisbank.loanofferservice.service.EducationalLoanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/v1/educationalLoan")
class EducatinalLoanController {

    @Autowired
    private val educationalLoanService: EducationalLoanService? = null

    @GetMapping("/")
    fun welcomeMessage(): Mono<String>? {
        return Mono.just("Welcome to the axis bank educational loan section")
    }

    @GetMapping("/allEducationalLoan")
    fun getAllEducationalLoan(): Flux<EducationalLoan?>? {
        return educationalLoanService!!.findAllEducationalLoan()
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addEducationalLoan(@RequestBody educationalLoan: @Valid EducationalLoan?): Mono<EducationalLoan?>? {
        return educationalLoanService!!.addNewEducationalLoan(educationalLoan)
    }

    @GetMapping("/{id}")
    fun getEducationalLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<EducationalLoan?>?>? {
        return educationalLoanService!!.findEducationalLoanById(id)
                ?.map { educationalLoan -> ResponseEntity.ok().body(educationalLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun getEducationalLoanByMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<EducationalLoan?>?>? {
        return educationalLoanService!!.findEducationalLoanByMobileNo(mobileNo)
                ?.map { educationalLoan -> ResponseEntity.ok().body(educationalLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @PutMapping("/{id}")
    fun updateExistingLoanById(@PathVariable("id") id: String?, @RequestBody educationalLoan: EducationalLoan?): Mono<ResponseEntity<EducationalLoan?>?>? {
        return educationalLoanService!!.updateExistingLoanById(id, educationalLoan!!)
                ?.map { educationalLoan1 -> ResponseEntity.ok().body(educationalLoan1) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEducationalLoanById(@PathVariable("id") id: String?): Mono<Void?>? {
        return educationalLoanService!!.deleteEducationalLoanById(id)
    }
}