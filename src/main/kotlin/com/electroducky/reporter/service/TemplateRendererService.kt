package com.electroducky.reporter.service

import com.electroducky.reporter.model.Report
import com.electroducky.reporter.model.ReportParameters
import org.springframework.stereotype.Service

@Service
class TemplateRendererService {
    fun render(reportParameters: ReportParameters, template: String): Report {
        return Report(template)
    }
}