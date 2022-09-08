package com.axisbank.servicecustomerloaninfo.client

import com.axisbank.servicecustomerloaninfo.domain.HousingLoan
import com.axisbank.servicecustomerloaninfo.exception.HousingLoanCleintException
import com.axisbank.servicecustomerloaninfo.exception.HousingLoanServerExeption
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Function

@Component
class HousingLoanWebClient {
    @Autowired
    private val webClient: WebClient? = null

    @Value("\${restClient.housingUrl}")
    private val housingUrl: String? = null

    fun returiveHousingLoanUsingMobileNo(mobileNo: Long?): Mono<HousingLoan?>? {
        val url = "$housingUrl/{mobileNo}"
        return webClient
                ?.get()
                ?.uri(url, mobileNo)
                ?.retrieve()
               /* ?.onStatus({ obj: HttpStatus -> obj.is4xxClientError }) { clientResponse: ClientResponse ->
                    println("Housing lona Status code is: {}"+ clientResponse.statusCode().value())
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return@onStatus Mono.empty<Throwable>()
                    }
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String? ->
                                Mono.error(HousingLoanCleintException(
                                        responseMessage!!
                                ))
                            })
                }
                ?.onStatus({ obj: HttpStatus -> obj.is5xxServerError }) { clientResponse: ClientResponse ->
                   println("Status code is: {}"+ clientResponse.statusCode().value())
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String ->
                                Mono.error(HousingLoanServerExeption(
                                        "Housing lona Server exception is :$responseMessage"
                                ))
                            })
                }*/
                ?.bodyToMono(HousingLoan::class.java)
                ?.log()
    }
}