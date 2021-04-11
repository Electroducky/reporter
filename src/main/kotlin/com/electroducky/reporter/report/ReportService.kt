package com.electroducky.reporter.report

import com.electroducky.reporter.sender.DispatcherService
import com.electroducky.reporter.template.TemplateRendererService
import com.electroducky.reporter.template.TemplateService
import org.springframework.stereotype.Service

@Service
class ReportService(
    val templateService: TemplateService,
    val templateRendererService: TemplateRendererService,
    val dispatcherService: DispatcherService
) {
    fun send(reportParameters: ReportParameters) {
        val template = templateService.findById(reportParameters.templateId)
        val report = templateRendererService.render(reportParameters.variables, template.templateText)
        dispatcherService.dispatch(report, template.recipients)
    }

    fun validateReportParameters(reportParameters: ReportParameters) {
        val template = templateService.findById(reportParameters.templateId)
        templateRendererService.render(reportParameters.variables, template.templateText)
    }
}