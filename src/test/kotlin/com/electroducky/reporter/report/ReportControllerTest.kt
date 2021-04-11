package com.electroducky.reporter.report

import com.electroducky.reporter.utils.anyNonNull
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(ReportController::class)
internal class ReportControllerTest(
    @Autowired
    private val mapper: ObjectMapper,
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var reportService: ReportService

    @Test
    fun send() {
        val rawReport = ReportParameters("test1", mutableMapOf("name" to "test"))
        Mockito.doNothing().`when`(reportService).send(anyNonNull())

        mockMvc.post("/api/v1/report") {
            contentType = MediaType.APPLICATION_JSON
            content = (mapper.writeValueAsString(rawReport))
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }
}