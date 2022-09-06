package com.axisbank.loanofferservice.repository

import com.axisbank.loanofferservice.domain.EducationalLoan
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EducationalLoanRepository : ReactiveMongoRepository<EducationalLoan?, String?> {
    fun findEducationalLoanByCustomerMobileNo(mobileNo: Long?): Mono<EducationalLoan?>?
}
