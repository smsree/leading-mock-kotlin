package com.axisbank.servicecustomerloaninfo.client

import com.axisbank.servicecustomerloaninfo.domain.EducationalLoan
import com.axisbank.servicecustomerloaninfo.exception.EducationalLoanClientException
import com.axisbank.servicecustomerloaninfo.exception.EducationalLoanServerException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Function

@Component
class EducationalLoanWebclient {
    @Autowired
    private val webClient: WebClient? = null

    @Value("\${restClient.educationalUrl}")
    private val educationalLoanUrl: String? = null

    fun retriveEducationalLoanDetailsViaMobileNo(mobileNo: Long?): Mono<EducationalLoan?>? {
        val url = "$educationalLoanUrl/{mobileNo}"
        return webClient
                ?.get()
                ?.uri(url, mobileNo)
                ?.retrieve()
               /* ?.onStatus({ obj: HttpStatus -> obj.is4xxClientError }) { clientResponse: ClientResponse ->
                    println("Educational lona Status code is: {}"+ clientResponse.statusCode().value())
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        return@onStatus Mono.empty()
                    }
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String? ->
                                Mono.error(EducationalLoanClientException(
                                        responseMessage!!
                                ))
                            })
                }
                ?.onStatus({ obj: HttpStatus -> obj.is5xxServerError }) { clientResponse: ClientResponse ->
                    println("Status code is: {}"+ clientResponse.statusCode().value())
                    clientResponse.bodyToMono(String::class.java)
                            .flatMap(Function<String, Mono<out Throwable>> { responseMessage: String ->
                                Mono.error(EducationalLoanServerException(
                                        "Educational lona Server exception is :$responseMessage"
                                ))
                            })
                }*/
                ?.bodyToMono(EducationalLoan::class.java)
                ?.log()
    }
}