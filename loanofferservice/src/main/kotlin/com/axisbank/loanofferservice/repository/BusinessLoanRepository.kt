package com.axisbank.loanofferservice.repository

import com.axisbank.loanofferservice.domain.BusinessLoan
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface BusinessLoanRepository : ReactiveMongoRepository<BusinessLoan?, String?> {
    fun findBusinessLoanByCustomerMobileNo(mobileNo: Long?): Mono<BusinessLoan?>?
}