package com.electroducky.reporter.service

import com.electroducky.reporter.model.ReportParameters
import org.springframework.stereotype.Service

@Service
class ReportService(
    val templateService: TemplateService,
    val templateRendererService: TemplateRendererService,
    val dispatcherService: DispatcherService
) {
    fun send(reportParameters: ReportParameters) {
        val template = templateService.findById(reportParameters.templateId)
        val report = templateRendererService.render(reportParameters, template.templateText)
        dispatcherService.dispatch(report, template.recipients)
    }
}