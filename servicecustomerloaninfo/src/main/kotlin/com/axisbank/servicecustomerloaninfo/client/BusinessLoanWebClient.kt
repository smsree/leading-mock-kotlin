package com.axisbank.servicecustomerloaninfo.client

import com.axisbank.servicecustomerloaninfo.domain.BusinessLoan
import com.axisbank.servicecustomerloaninfo.exception.BusinessLoanClientException
import com.axisbank.servicecustomerloaninfo.exception.BusinessLoanServerException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Function

@Component
class BusinessLoanWebClient {
    @Autowired
    private val webClient: WebClient? = null

    @Value("\${restClient.businessUrl}")
    private val businessUrl: String? = null

    fun retriveBusinessLoanUsingMobileNo(mobileNo: Long?): Mono<BusinessLoan?>? {
        val url = "$businessUrl/{mobileNo}"
        return webClient
                ?.get()
                ?.uri(url, mobileNo)
                ?.retrieve()
                /*?.onStatus({ obj: HttpStatus -> obj.is4xxClientError }) { clientResponse: ClientResponse ->
                    println("Business lona Status code is: {}"+ clientResponse.statusCode().value())
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return@onStatus Mono.empty<Throwable>()
                    }
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String? ->
                                Mono.error(BusinessLoanClientException(
                                        responseMessage!!
                                ))
                            })
                }
                ?.onStatus({ obj: HttpStatus -> obj.is5xxServerError }) { clientResponse: ClientResponse ->
                    println("Status code is: {}"+ clientResponse.statusCode().value())
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String ->
                                Mono.error(BusinessLoanServerException(
                                        "Business lona Server exception is :$responseMessage"
                                ))
                            })
                }*/
                ?.bodyToMono(BusinessLoan::class.java)
                ?.log()
    }
}