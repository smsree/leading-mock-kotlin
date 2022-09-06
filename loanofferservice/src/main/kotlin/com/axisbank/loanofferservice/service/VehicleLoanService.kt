package com.axisbank.loanofferservice.service

import com.axisbank.loanofferservice.domain.VehicleLoan
import com.axisbank.loanofferservice.repository.VehicleLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class VehicleLoanService {
    @Autowired
    private val vehicleLoanRepository: VehicleLoanRepository? = null

    fun addNewLoan(vehicleLoan: VehicleLoan?): Mono<VehicleLoan?>? {
        return vehicleLoanRepository!!.save(vehicleLoan!!)
    }

    fun getVehicleLoanById(id: String?): Mono<VehicleLoan?>? {
        return vehicleLoanRepository!!.findById(id!!)
    }

    fun getVehicleLoanByMobileNo(mobileNo: Long?): Mono<VehicleLoan?>? {
        return vehicleLoanRepository!!.findVehicleLoanByCustomerMobileNo(mobileNo)
    }

    fun updateVehcileLoanById(id: String?, updateVehicleLoan: VehicleLoan): Mono<VehicleLoan?>? {
        val existingMono = vehicleLoanRepository!!.findById(id!!)
        return existingMono
                .flatMap { existingLoan: VehicleLoan? ->
                    existingLoan!!.setCustomerMobileNo(updateVehicleLoan.getCustomerMobileNo())
                    existingLoan.setVehicleName(updateVehicleLoan.getVehicleName())
                    existingLoan.setLoanName(updateVehicleLoan.getLoanName())
                    existingLoan.setLoanamount(updateVehicleLoan.getLoanamount())
                    existingLoan.setRateOfInterest(updateVehicleLoan.getRateOfInterest())
                    existingLoan.setStatus(updateVehicleLoan.getStatus())
                    vehicleLoanRepository.save(existingLoan)
                }
    }

    fun deleteVehicleLoanById(id: String?): Mono<Void?>? {
        return vehicleLoanRepository!!.deleteById(id!!)
    }

    fun getAllVehicleLoan(): Flux<VehicleLoan?>? {
        return vehicleLoanRepository!!.findAll()
    }
}