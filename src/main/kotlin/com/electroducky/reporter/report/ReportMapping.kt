package com.electroducky.reporter.report

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/report")
interface ReportMapping {
    @PostMapping
    fun send(@RequestBody reportParameters: ReportParameters)
}