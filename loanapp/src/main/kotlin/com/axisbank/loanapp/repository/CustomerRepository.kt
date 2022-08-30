package com.axisbank.loanapp.repository

import com.axisbank.loanapp.domain.Customer
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


@Repository
interface CustomerRepository : ReactiveMongoRepository<Customer?, String?> {
    fun findByMobileNo(mobileNo: Long?): Mono<Customer?>?
}
