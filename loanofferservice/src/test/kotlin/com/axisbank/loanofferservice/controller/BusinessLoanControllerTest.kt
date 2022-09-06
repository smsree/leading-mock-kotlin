package com.axisbank.loanofferservice.controller

import com.axisbank.loanofferservice.domain.BusinessLoan
import com.axisbank.loanofferservice.service.BusinessLoanService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
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

@WebFluxTest(controllers = [BusinessLoanController::class])
@AutoConfigureWebTestClient
class BusinessLoanControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    private val businessLoanServiceMock: BusinessLoanService? = null

    @Test
    fun welcomeTestMethod() {
        webTestClient
                ?.get()
                ?.uri("/v1/businessLoan/")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(String::class.java)
                ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                    val responseBody = stringEntityExchangeResult.responseBody
                    Assertions.assertEquals(responseBody, "Welcome to the axis bank business loan section")
                }
    }

    @Test
    fun getAllLoanTest() {
        val businessLoan = BusinessLoan("id", "farming", 2L, "mitra loan", 1000, 2.1, "applied")
        Mockito.`when`(businessLoanServiceMock!!.getAllBusinessLoanAvailable())
                .thenReturn(Flux.just(businessLoan))
        val responseBody: Flux<BusinessLoan> = webTestClient
                ?.get()
                ?.uri("/v1/businessLoan/allBuisnessLoan")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.returnResult(BusinessLoan::class.java)
                ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }

    @Test
    fun addLoanMethodTest() {
        val businessLoan = BusinessLoan("id", "farming", 2L, "mitra loan", 1000, 2.1, "applied")
        Mockito.`when`(businessLoanServiceMock!!.addNewBusinessLoan(ArgumentMatchers.isA(BusinessLoan::class.java)))
                .thenReturn(Mono.just(businessLoan))
        webTestClient
                ?.post()
                ?.uri("/v1/businessLoan/")
                ?.bodyValue(businessLoan)
                ?.exchange()
                ?.expectStatus()
                ?.isCreated
                ?.expectBody(BusinessLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun getLoanByCustomerMobileNo() {
        val businessLoan = BusinessLoan("id", "farming", 2L, "mitra loan", 1000, 2.1, "applied")
        Mockito.`when`(businessLoanServiceMock!!.findBusinessLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
                .thenReturn(Mono.just(businessLoan))
        webTestClient
                ?.get()
                ?.uri("/v1/businessLoan/customerMobileNo/2")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(BusinessLoan::class.java)
                ?.consumeWith { educationalLoanEntityExchangeResult ->
                    val responseBody = educationalLoanEntityExchangeResult.responseBody
                    assert(responseBody!!.getCustomerMobileNo()!! >= 2)
                }
    }

    @Test
    fun deleteMethodTest() {
        Mockito.`when`(businessLoanServiceMock!!.deleteBusinessLoanByIdService(ArgumentMatchers.isA(String::class.java)))
                .thenReturn(Mono.empty())
        webTestClient
                ?.delete()
                ?.uri("/v1/businessLoan/id")
                ?.exchange()
                ?.expectStatus()
                ?.isNoContent
    }

}