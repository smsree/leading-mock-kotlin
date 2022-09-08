package com.axisbank.servicecustomerloaninfo.client

import com.axisbank.servicecustomerloaninfo.domain.Customer
import com.axisbank.servicecustomerloaninfo.exception.CustomerClientException
import com.axisbank.servicecustomerloaninfo.exception.CustomerServerException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.function.Function

@Component
class CustomerWebClient {
    @Autowired
    private val webClient: WebClient? = null

    @Value("\${restClient.customerUrl}")
    var customerUrl: String? = null

    fun retriveCustomerInfoByMobileNo(mobileNo: Long): Customer {
        val url = "$customerUrl/mobile/{mobileNo}"
        return webClient
                ?.get()
                ?.uri(url, mobileNo)
                ?.retrieve()
               /* ?.onStatus({ obj: HttpStatus -> obj.is4xxClientError }) { clientResponse: ClientResponse ->
                    println("Status code is: {}"+ clientResponse.statusCode().value())
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return@onStatus Mono.error(CustomerClientException("There is no customer info available:$mobileNo",
                                clientResponse.statusCode().value()))
                    }
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String? ->
                                Mono.error(CustomerClientException(
                                        responseMessage!!, clientResponse.statusCode().value()
                                ))
                            })
                }
                ?.onStatus({ obj: HttpStatus -> obj.is5xxServerError }) { clientResponse: ClientResponse ->
                    println("Status code is: {}"+ clientResponse.statusCode().value())
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String ->
                                Mono.error(CustomerServerException(
                                        "Customer Server exception is :$responseMessage"
                                ))
                            })
                }*/
                ?.bodyToMono(Customer::class.java)
                ?.log()
                ?.block()!!;

    }
}