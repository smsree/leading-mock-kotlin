package com.axisbank.loanofferservice.repository

import com.axisbank.loanofferservice.domain.VehicleLoan
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface VehicleLoanRepository : ReactiveMongoRepository<VehicleLoan?, String?> {
    fun findVehicleLoanByCustomerMobileNo(mobileNo: Long?): Mono<VehicleLoan?>?
}
