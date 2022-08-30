package com.axisbank.loanapp.controller

import com.axisbank.loanapp.domain.Customer
import com.axisbank.loanapp.service.CustomerService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.isA
import  org.mockito.Mockito.`when`

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest

import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier


@WebFluxTest(controllers = [CustomerController::class])
@AutoConfigureWebTestClient
internal class CustomerControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    val customerServiceMock: CustomerService? = null



    @Test
    fun welcomeMsgTest()
        {
            webTestClient
                    ?.get()
                    ?.uri("/v1/customer/")
                    ?.exchange()
                    ?.expectStatus()
                    ?.is2xxSuccessful
                    ?.expectBody(String::class.java)
                    ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                        val responseBody = stringEntityExchangeResult.responseBody
                        Assertions.assertEquals("Welcome to Axis bank", responseBody)
                    }
        }

    @Test
    fun allCustomerTest()
        {
            val customer = Customer("abc", "name", "business", 909090909L, "asdfasfd", 98789789L, "1999-09-08")
            Mockito.`when`(customerServiceMock!!.allCustomer)
                    .thenReturn(Flux.just(customer))
            val responseBody = webTestClient
                    ?.get()
                    ?.uri("/v1/customer/all")
                    ?.exchange()
                    ?.expectStatus()
                    ?.is2xxSuccessful
                    ?.returnResult(Customer::class.java)
                    ?.responseBody
            StepVerifier.create(responseBody!!).expectNextCount(1).verifyComplete()
        }

    @Test
    fun cusotmerByIdTest()
         {
            val id = "abc"
            val customer = Customer("abc", "name", "business", 909090909L, "asdfasfd", 98789789L, "1999-09-08")
            Mockito.`when`(customerServiceMock!!.getCustomerById(id))
                    .thenReturn(Mono.just(customer))
            webTestClient
                    ?.get()
                    ?.uri("/v1/customer/{id}", id)
                    ?.exchange()
                    ?.expectStatus()
                    ?.is2xxSuccessful
                    ?.expectBody(Customer::class.java)
                    ?.consumeWith { customerEntityExchangeResult: EntityExchangeResult<Customer?> ->
                        val responseBody = customerEntityExchangeResult.responseBody
                        assert(responseBody!!.getCustomerId() == id)
                    }
        }

    @Test
    fun addCustomerTest() {
        val customer = Customer();
        customer.setCustomerId("abc");
        customer.setDob("dob")
        customer.setAdharNo(123L)
        customer.setWork("work")
        customer.setName("mname")
        customer.setPanNo("adf")
        customer.setMobileNo(123)
        Mockito.`when`(customerServiceMock!!.addNewCutomer(customer))
                .thenReturn(Mono.just(customer))
        webTestClient
                ?.post()
                ?.uri("/v1/customer/")
                ?.bodyValue(customer)
                ?.exchange()
                ?.expectStatus()
                ?.isCreated
                ?.expectBody(Customer::class.java)
                ?.consumeWith { customerEntityExchangeResult: EntityExchangeResult<Customer?> ->
                    val responseBody = customerEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun updateExistingCustomerTest() {
        val id = "abc"
        val customer = Customer("abc", "name", "business", 909090909L, "asdfasfd", 98789789L, "1999-09-08")
        Mockito.`when`(customerServiceMock!!.updateCustomerById(id, customer))
                .thenReturn(Mono.just(customer))
        webTestClient
                ?.put()
                ?.uri("/v1/customer/{id}", id)
                ?.bodyValue(customer)
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(Customer::class.java)
                ?.consumeWith { customerEntityExchangeResult: EntityExchangeResult<Customer?> ->
                    val responseBody = customerEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun deleteByIdTest() {
        val id = "abc"
        val customer = Customer("abc", "name", "business", 909090909L, "asdfasfd", 98789789L, "1999-09-08")
        Mockito.`when`(customerServiceMock!!.deleteCustomerById(id))
                .thenReturn(Mono.empty())
        webTestClient
                ?.delete()
                ?.uri("/v1/customer/{id}", id)
                ?.exchange()
                ?.expectStatus()
                ?.isNoContent
    }
}