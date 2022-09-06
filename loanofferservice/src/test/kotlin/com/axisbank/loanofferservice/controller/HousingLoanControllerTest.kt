package com.axisbank.loanofferservice.controller

import com.axisbank.loanofferservice.domain.HousingLoan
import com.axisbank.loanofferservice.service.HousingLoanService
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

@WebFluxTest(controllers = [HousingLoanController::class])
@AutoConfigureWebTestClient
class HousingLoanControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    private val housingLoanService: HousingLoanService? = null

    @Test
    fun welcomeMethodTest() {
        webTestClient
                ?.get()
                ?.uri("/v1/housingLoan/")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(String::class.java)
                ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                    val responseBody = stringEntityExchangeResult.responseBody
                    Assertions.assertEquals(responseBody, "Welcome to axis bank housing loan section")
                }
    }

    @Test
    fun getAllLoanTest() {
        val housingLoan = HousingLoan("id", 2L, "address", "housing loan", 12, 2.3, "applied")
        Mockito.`when`(housingLoanService!!.findAllHousingLoan())
                .thenReturn(Flux.just(housingLoan))
        val responseBody: Flux<HousingLoan> = webTestClient
                ?.get()
                ?.uri("/v1/housingLoan/allHousingLoan")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.returnResult(HousingLoan::class.java)
                ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }

    @Test
    fun getHousingLoanByCustomerMobileNo() {
        val housingLoan = HousingLoan("id", 2L, "address", "housing loan", 12, 2.3, "applied")
        Mockito.`when`(housingLoanService!!.findHousingLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
                .thenReturn(Mono.just(housingLoan))
        webTestClient
                ?.get()
                ?.uri("/v1/housingLoan/customerMobileNo/2")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(HousingLoan::class.java)
                ?.consumeWith { educationalLoanEntityExchangeResult ->
                    val responseBody = educationalLoanEntityExchangeResult.responseBody
                    assert(responseBody!!.getCustomerMobileNo()!! >= 2)
                }
    }

    @Test
    fun updateLoanTest() {
        val id = "id"
        val housingLoan = HousingLoan("id", 2L, "address", "housing loan", 12, 2.3, "applied")
        Mockito.`when`(housingLoanService!!.updateHousingLoanById(ArgumentMatchers.isA(String::class.java), ArgumentMatchers.isA(HousingLoan::class.java)))
                .thenReturn(Mono.just(housingLoan))
        webTestClient
                ?.put()
                ?.uri("/v1/housingLoan/id")
                ?.bodyValue(housingLoan)
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(HousingLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun deleteMethodTest() {
        Mockito.`when`(housingLoanService!!.deleteHousingLoanById(ArgumentMatchers.isA(String::class.java)))
                .thenReturn(Mono.empty())
        webTestClient
                ?.delete()
                ?.uri("/v1/housingLoan/id")
                ?.exchange()
                ?.expectStatus()
                ?.isNoContent
    }
}