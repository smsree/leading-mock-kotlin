package com.axisbank.loanofferservice.service

import com.axisbank.loanofferservice.domain.EducationalLoan
import com.axisbank.loanofferservice.repository.EducationalLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EducationalLoanService {
    @Autowired
    private val educationalLoanRepository: EducationalLoanRepository? = null
    fun findAllEducationalLoan(): Flux<EducationalLoan?>? {
        return educationalLoanRepository!!.findAll()
    }

    fun addNewEducationalLoan(educationalLoan: EducationalLoan?): Mono<EducationalLoan?>? {
        return educationalLoanRepository!!.save(educationalLoan!!)
    }

    fun findEducationalLoanById(id: String?): Mono<EducationalLoan?>? {
        return educationalLoanRepository!!.findById(id!!)
    }

    fun findEducationalLoanByMobileNo(mobileNo: Long?): Mono<EducationalLoan?>? {
        return educationalLoanRepository!!.findEducationalLoanByCustomerMobileNo(mobileNo)
    }

    fun updateExistingLoanById(id: String?, updatedEducationalLoan: EducationalLoan): Mono<EducationalLoan?>? {
        val existingMono = educationalLoanRepository!!.findById(id!!)
        return existingMono.flatMap { existingLoan: EducationalLoan? ->
            existingLoan!!.setCustomerMobileNo(updatedEducationalLoan.getCustomerMobileNo())
            existingLoan.setCollegeName(updatedEducationalLoan.getCollegeName())
            existingLoan.setLoanName(updatedEducationalLoan.getLoanName())
            existingLoan.setLoanamount(updatedEducationalLoan.getLoanamount())
            existingLoan.setStatus(updatedEducationalLoan.getStatus())
            existingLoan.setRateOfInterest(updatedEducationalLoan.getRateOfInterest())
            educationalLoanRepository.save(existingLoan)
        }
    }

    fun deleteEducationalLoanById(id: String?): Mono<Void?>? {
        return educationalLoanRepository!!.deleteById(id!!)
    }
}