package com.electroducky.reporter.sender

import com.electroducky.reporter.report.Report
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [(HttpSenderService::class), (WebClientConfig::class)])
internal class HttpSenderServiceTest(@Autowired val httpSenderService: HttpSenderService) {

    @Test
    fun `send should throw exception when wrong url`() {
        val report = Report("test")
        val endpoint = "test"
        assertThrows(IllegalStateException::class.java) { httpSenderService.send(report, endpoint) }
    }

    @Test
    fun `send should work correctly`() {
        val report = Report("test")
        val endpoint = "https://httpbin.org/post"
        httpSenderService.send(report, endpoint)
    }
}