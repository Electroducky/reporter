package com.electroducky.reporter.schedule

import com.electroducky.reporter.model.ReportParameters
import com.electroducky.reporter.service.ReportService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.annotation.Autowired

class ScheduledReportJob : Job {
    @Autowired
    private lateinit var reportService: ReportService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    override fun execute(context: JobExecutionContext) {
        val persistParams = context.mergedJobDataMap[scheduledReportData] as String
        val scheduledReport = objectMapper.readValue<ScheduledReportParameters>(persistParams)
        println("Running report job ${scheduledReport.name} by ${scheduledReport.cronExpression}")
        reportService.send(ReportParameters(scheduledReport.templateId, scheduledReport.variables))
    }

    companion object {
        const val scheduledReportData = "scheduledReport"
    }
}