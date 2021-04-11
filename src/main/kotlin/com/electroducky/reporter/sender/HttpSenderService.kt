package com.electroducky.reporter.sender

import com.electroducky.reporter.report.Report
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class HttpSenderService(
    val webClient: WebClient
) : SenderService {

    override val type: String get() = "http"

    override fun send(report: Report, endpoint: String) {
        webClient
            .post()
            .uri(endpoint)
            .body(Mono.just(report), Report::class.java)
            .retrieve()
            .toBodilessEntity()
            .onErrorResume { error -> Mono.error(IllegalStateException(error.message)) }
            .block()
    }
}