package com.axisbank.loanofferservice.controller

import com.axisbank.loanofferservice.domain.VehicleLoan
import com.axisbank.loanofferservice.service.VehicleLoanService
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

@WebFluxTest(controllers = [VehicleLoanController::class])
@AutoConfigureWebTestClient
class VehicleLoanControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    private val vehicleLoanServiceMock: VehicleLoanService? = null

    @Test
    fun welcomeTestController() {
        webTestClient
                ?.get()
                ?.uri("/v1/vehicleLoan/")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(String::class.java)
                ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                    val responseBody = stringEntityExchangeResult.responseBody
                    Assertions.assertEquals(responseBody, "Welcome to axis bank vehicle loan section")
                }
    }

    @Test
    fun addNewVehicleLoanTest() {
        val vehicle = VehicleLoan("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied")
        Mockito.`when`(vehicleLoanServiceMock!!.addNewLoan(ArgumentMatchers.isA(VehicleLoan::class.java)))
                .thenReturn(Mono.just(vehicle))
        webTestClient
                ?.post()
                ?.uri("/v1/vehicleLoan/")
                ?.bodyValue(vehicle)
                ?.exchange()
                ?.expectStatus()
                ?.isCreated
                ?.expectBody(VehicleLoan::class.java)
                ?.consumeWith { vehicleLoanEntityExchangeResult ->
                    val responseBody: VehicleLoan? = vehicleLoanEntityExchangeResult.getResponseBody()
                    assert(responseBody!!.getVehicleName().equals("scooter"))
                }
    }

    @Test
    fun getAllLoanTest() {
        val vehicle = VehicleLoan("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied")
        Mockito.`when`(vehicleLoanServiceMock!!.getAllVehicleLoan())
                .thenReturn(Flux.just(vehicle))
        val responseBody = webTestClient
                ?.get()
                ?.uri("/v1/vehicleLoan/allVehicleLoan")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.returnResult(VehicleLoan::class.java)
                ?.responseBody
        StepVerifier.create(responseBody!!).expectNextCount(1).verifyComplete()
    }

    @Test
    fun getEducationalLoanByCustomerMobileNo() {
        val vehicle = VehicleLoan("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied")
        Mockito.`when`(vehicleLoanServiceMock!!.getVehicleLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
                .thenReturn(Mono.just(vehicle))
        webTestClient
                ?.get()
                ?.uri("/v1/vehicleLoan/customerMobileNo/2")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(VehicleLoan::class.java)
                ?.consumeWith { vehicleLoanEntityExchangeResult ->
                    val responseBody = vehicleLoanEntityExchangeResult.responseBody
                    assert(responseBody!!.getCustomerMobileNo()!! >= 2)
                }
    }

    @Test
    fun getLoanById() {
        val vehicle = VehicleLoan("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied")
        Mockito.`when`(vehicleLoanServiceMock!!.getVehicleLoanById(ArgumentMatchers.isA(String::class.java)))
                .thenReturn(Mono.just(vehicle))
        webTestClient
                ?.get()
                ?.uri("/v1/vehicleLoan/id")
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(VehicleLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody
                    assert(responseBody!!.getVehicleLoanId().equals("id"))
                }
    }

    @Test
    fun updateLoanTest() {
        val id = "id"
        val vehicle = VehicleLoan("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied")
        Mockito.`when`(vehicleLoanServiceMock!!.updateVehcileLoanById(ArgumentMatchers.isA(String::class.java), ArgumentMatchers.isA(VehicleLoan::class.java)))
                .thenReturn(Mono.just(vehicle))
        webTestClient
                ?.put()
                ?.uri("/v1/vehicleLoan/id")
                ?.bodyValue(vehicle)
                ?.exchange()
                ?.expectStatus()
                ?.is2xxSuccessful
                ?.expectBody(VehicleLoan::class.java)
                ?.consumeWith { loanEntityExchangeResult ->
                    val responseBody = loanEntityExchangeResult.responseBody!!
                }
    }

    @Test
    fun deleteMethodTest() {
        Mockito.`when`(vehicleLoanServiceMock!!.deleteVehicleLoanById(ArgumentMatchers.isA(String::class.java)))
                .thenReturn(Mono.empty())
        webTestClient
                ?.delete()
                ?.uri("/v1/vehicleLoan/id")
                ?.exchange()
                ?.expectStatus()
                ?.isNoContent
    }
}