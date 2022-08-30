package com.axisbank.loanapp.service

import com.axisbank.loanapp.domain.Customer
import com.axisbank.loanapp.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class CustomerService {
    @Autowired
    private val customerRepository: CustomerRepository? = null
    fun addNewCutomer(customer: Customer): Mono<Customer> {
        return customerRepository!!.save(customer)
    }

    val allCustomer: Flux<Customer?>
        get() = customerRepository!!.findAll()

    fun getCustomerById(customerId: String): Mono<Customer?> {
        return customerRepository!!.findById(customerId)
    }

    fun updateCustomerById(customerId: String?, updatedCustomer: Customer): Mono<Customer?>? {
        val customerMono = customerRepository!!.findById(customerId!!)
        return customerMono.flatMap { customer: Customer? ->
            customer!!.setName(updatedCustomer.getName())
            customer.setWork(updatedCustomer.getWork())
            customer.setAdharNo(updatedCustomer.getAdharNo())
            customer.setDob(updatedCustomer.getDob())
            customer.setPanNo(updatedCustomer.getPanNo())
            customerRepository.save<Customer?>(customer)
        }
    }

    fun getCustomerByMobileNo(mobileNo: Long?): Mono<Customer?>? {
        return customerRepository!!.findByMobileNo(mobileNo)
    }

    fun deleteCustomerById(id: String): Mono<Void> {
        return customerRepository!!.deleteById(id)
    }
}