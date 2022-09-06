package com.axisbank.loanofferservice.controller
import com.axisbank.loanofferservice.domain.HousingLoan
import com.axisbank.loanofferservice.service.HousingLoanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/v1/housingLoan")
class HousingLoanController {
    @Autowired
    private val housingLoanService: HousingLoanService? = null

    @GetMapping("/")
    fun welcomeMessage(): Mono<String>? {
        return Mono.just("Welcome to axis bank housing loan section")
    }

    @PostMapping("/")
    fun addNewHousingLoan(@RequestBody housingLoan: @Valid HousingLoan?): Mono<HousingLoan?>? {
        return housingLoanService!!.addNewHousingLoan(housingLoan)
    }

    @GetMapping("/allHousingLoan")
    fun getallHousingLoan(): Flux<HousingLoan?>? {
        return housingLoanService!!.findAllHousingLoan()
    }

    @GetMapping("/{id}")
    fun findHousingLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<HousingLoan?>?>? {
        return housingLoanService!!.findHousingLoanById(id)
                ?.map { housingLoan -> ResponseEntity.ok().body(housingLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun findHousingLoanByCustomerMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<HousingLoan?>?>? {
        return housingLoanService!!.findHousingLoanByMobileNo(mobileNo)
                ?.map { housingLoan -> ResponseEntity.ok().body(housingLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @PutMapping("/{id}")
    fun updateHousingLoanById(@PathVariable("id") id: String?, @RequestBody updatedHousingLoan: HousingLoan?): Mono<ResponseEntity<HousingLoan?>?>? {
        return housingLoanService!!.updateHousingLoanById(id, updatedHousingLoan!!)
                ?.map { housingLoan -> ResponseEntity.ok().body(housingLoan) }
                ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHousingLoan(@PathVariable("id") id: String?): Mono<Void?>? {
        return housingLoanService!!.deleteHousingLoanById(id)
    }
}