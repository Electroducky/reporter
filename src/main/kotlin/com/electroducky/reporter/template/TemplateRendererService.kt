package com.electroducky.reporter.template

import com.electroducky.reporter.report.Report
import com.electroducky.reporter.report.ReportParameters
import org.springframework.stereotype.Service

@Service
class TemplateRendererService {
    fun render(reportParameters: ReportParameters, template: String): Report {
        return Report(template)
    }
}