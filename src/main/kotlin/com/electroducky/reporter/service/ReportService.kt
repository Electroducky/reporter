package com.electroducky.reporter.service

import com.electroducky.reporter.model.RawReport
import com.electroducky.reporter.model.Report
import org.springframework.stereotype.Service

@Service
class ReportService(
    val templateService: TemplateService,
    val templateRendererService: TemplateRendererService,
    val dispatcherService: DispatcherService
) {
    fun send(rawReport: RawReport): Report {
        TODO("Not yet implemented")
    }
}