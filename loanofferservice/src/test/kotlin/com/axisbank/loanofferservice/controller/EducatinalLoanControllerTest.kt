package com.axisbank.loanofferservice.controller

import com.axisbank.loanofferservice.domain.EducationalLoan
import com.axisbank.loanofferservice.service.EducationalLoanService
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

@WebFluxTest(controllers = [EducatinalLoanController::class])
@AutoConfigureWebTestClient
class EducatinalLoanControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    private val educationalLoanServiceMock: EducationalLoanService? = null

    @Test
    fun welcomeTestMethod() {
        webTestClient
                ?.get()
                ?.uri("/v1/educationalLoan/")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(String::class.java)
                ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                    val responseBody = stringEntityExchangeResult.responseBody
                    Assertions.assertEquals(responseBody, "Welcome to the axis bank educational loan section")
                }
    }

    @Test
    fun getAllLoanTest() {
        val educationalLoan = EducationalLoan("id", "abc", 2L, "abc", 10, 2.4, "applied")
        Mockito.`when`(educationalLoanServiceMock!!.findAllEducationalLoan())
                .thenReturn(Flux.just(educationalLoan))
        val responseBody: Flux<EducationalLoan> = webTestClient
                ?.get()
                ?.uri("/v1/educationalLoan/allEducationalLoan")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.returnResult(EducationalLoan::class.java)
                ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }

    @Test
    fun addLoanMethodTest() {
        val educationalLoan = EducationalLoan("id", "abc", 2L, "abc", 10, 2.4, "applied")
        Mockito.`when`(educationalLoanServiceMock!!.addNewEducationalLoan(ArgumentMatchers.isA(EducationalLoan::class.java)))
                .thenReturn(Mono.just(educationalLoan))
        webTestClient
                ?.post()
                ?.uri("/v1/educationalLoan/")
                ?.bodyValue(educationalLoan)
                ?.exchange()
                ?.expectStatus()
                ?.isCreated
                ?.expectBody(EducationalLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun getEducationalLoanByCustomerMobileNo() {
        val educationalLoan = EducationalLoan("id", "abc", 2L, "abc", 10, 2.4, "applied")
        Mockito.`when`(educationalLoanServiceMock!!.findEducationalLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
                .thenReturn(Mono.just(educationalLoan))
        webTestClient
                ?.get()
                ?.uri("/v1/educationalLoan/customerMobileNo/2")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(EducationalLoan::class.java)
                ?.consumeWith { educationalLoanEntityExchangeResult ->
                    val responseBody = educationalLoanEntityExchangeResult.responseBody
                    assert(responseBody!!.getCustomerMobileNo()!! >= 2)
                }
    }

    @Test
    fun getLoanById() {
        val educationalLoan = EducationalLoan("id", "abc", 2L, "abc", 10, 2.4, "applied")
        Mockito.`when`(educationalLoanServiceMock!!.findEducationalLoanById(ArgumentMatchers.isA(String::class.java)))
                .thenReturn(Mono.just(educationalLoan))
        webTestClient
                ?.get()
                ?.uri("/v1/educationalLoan/id")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(EducationalLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody
                    assert(responseBody!!.getEducationalLoanId().equals("id"))
                }
    }

    @Test
    fun updateLoanTest() {
        val id = "id"
        val educationalLoan = EducationalLoan("id", "abc", 2L, "abc", 10, 2.4, "applied")
        Mockito.`when`(educationalLoanServiceMock!!.updateExistingLoanById(ArgumentMatchers.anyString(), ArgumentMatchers.isA(EducationalLoan::class.java!!)))
                .thenReturn(Mono.just(educationalLoan))
        webTestClient
                ?.put()
                ?.uri("/v1/educationalLoan/id")
                ?.bodyValue(educationalLoan)
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(EducationalLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun deleteMethodTest() {
        Mockito.`when`(educationalLoanServiceMock!!.deleteEducationalLoanById(ArgumentMatchers.isA(String::class.java)))
                .thenReturn(Mono.empty())
        webTestClient
                ?.delete()
                ?.uri("/v1/educationalLoan/id")
                ?.exchange()
                ?.expectStatus()
                ?.isNoContent
    }
}