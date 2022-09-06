package com.axisbank.loanofferservice.repository

import com.axisbank.loanofferservice.domain.HousingLoan
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface HousingLoanRepository : ReactiveMongoRepository<HousingLoan?, String?> {
    fun findHousingLoanByCustomerMobileNo(mobileNo: Long?): Mono<HousingLoan?>?
}
