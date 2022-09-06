package com.axisbank.loanofferservice.service

import com.axisbank.loanofferservice.domain.HousingLoan
import com.axisbank.loanofferservice.repository.HousingLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class HousingLoanService {
    @Autowired
    private val housingLoanRepository: HousingLoanRepository? = null

    fun addNewHousingLoan(housingLoan: HousingLoan?): Mono<HousingLoan?>? {
        return housingLoanRepository!!.save(housingLoan!!)
    }

    fun findAllHousingLoan(): Flux<HousingLoan?>? {
        return housingLoanRepository!!.findAll()
    }

    fun findHousingLoanById(id: String?): Mono<HousingLoan?>? {
        return housingLoanRepository!!.findById(id!!)
    }

    fun findHousingLoanByMobileNo(mobileNo: Long?): Mono<HousingLoan?>? {
        return housingLoanRepository!!.findHousingLoanByCustomerMobileNo(mobileNo)
    }

    fun updateHousingLoanById(id: String?, updatedHousingLoan: HousingLoan): Mono<HousingLoan?>? {
        val availableLoan = housingLoanRepository!!.findById(id!!)
        return availableLoan.flatMap { existingLoan: HousingLoan? ->
            existingLoan!!.setCustomerMobileNo(updatedHousingLoan.getCustomerMobileNo())
            existingLoan.setAddress(updatedHousingLoan.getAddress())
            existingLoan.setLoanName(updatedHousingLoan.getLoanName())
            existingLoan.setLoanamount(updatedHousingLoan.getLoanamount())
            existingLoan.setRateOfInterest(updatedHousingLoan.getRateOfInterest())
            existingLoan.setStatus(updatedHousingLoan.getStatus())
            housingLoanRepository.save(existingLoan)
        }
    }

    fun deleteHousingLoanById(id: String?): Mono<Void?>? {
        return housingLoanRepository!!.deleteById(id!!)
    }


}