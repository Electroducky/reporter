package com.electroducky.reporter.report

import org.springframework.web.bind.annotation.RestController

@RestController
class ReportController(
    private val reportService: ReportService
) : ReportMapping {

    override fun send(reportParameters: ReportParameters) {
        reportService.send(reportParameters)
    }
}