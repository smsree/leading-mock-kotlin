package com.axisbank.loanofferservice.service

import com.axisbank.loanofferservice.domain.BusinessLoan
import com.axisbank.loanofferservice.repository.BusinessLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BusinessLoanService {
    @Autowired
    private val businessLoanRepository: BusinessLoanRepository? = null
    fun addNewBusinessLoan(businessLoan: BusinessLoan?): Mono<BusinessLoan?>? {
        return businessLoanRepository!!.save(businessLoan!!)
    }

    fun getAllBusinessLoanAvailable(): Flux<BusinessLoan?>? {
        return businessLoanRepository!!.findAll()
    }

    fun findBusinessLoanById(id: String?): Mono<BusinessLoan?>? {
        return businessLoanRepository!!.findById(id!!)
    }

    fun findBusinessLoanByMobileNo(mobileNo: Long?): Mono<BusinessLoan?>? {
        return businessLoanRepository!!.findBusinessLoanByCustomerMobileNo(mobileNo)
    }

    fun updateExistingBusinessLoanByIdService(id: String?, updatedBusinessLoan: BusinessLoan): Mono<BusinessLoan?>? {
        val availableLoan = businessLoanRepository!!.findById(id!!)
        return availableLoan
                .flatMap { businessLoan: BusinessLoan? ->
                    businessLoan!!.setCustomerMobileNo(updatedBusinessLoan.getCustomerMobileNo())
                    businessLoan.setBusinessName(updatedBusinessLoan.getBusinessName())
                    businessLoan.setLoanName(updatedBusinessLoan.getLoanName())
                    businessLoan.setLoanamount(updatedBusinessLoan.getLoanamount())
                    businessLoan.setRateOfInterest(updatedBusinessLoan.getRateOfInterest())
                    businessLoan.setStatus(updatedBusinessLoan.getStatus())
                    businessLoanRepository.save(businessLoan)
                }
    }

    fun deleteBusinessLoanByIdService(id: String?): Mono<Void?>? {
        return businessLoanRepository!!.deleteById(id!!)
    }
}
