package com.electroducky.reporter.sender

import com.electroducky.reporter.common.logger
import com.electroducky.reporter.report.Report
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class HttpSenderService(
    val webClient: WebClient
) : SenderService {
    private val log = logger()

    override val type: String get() = "http"

    override fun send(report: Report, endpoint: String) {
        log.info("Sending report http request to $endpoint")

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