package com.electroducky.reporter.cotroller

import com.electroducky.reporter.model.ReportParameters
import com.electroducky.reporter.service.ReportService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/report")
class ReportController(
    private val reportService: ReportService
) {

    @PostMapping
    fun send(@RequestBody reportParameters: ReportParameters) {
        reportService.send(reportParameters)
    }
}