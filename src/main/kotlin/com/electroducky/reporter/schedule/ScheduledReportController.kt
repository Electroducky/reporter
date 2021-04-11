package com.electroducky.reporter.schedule

import org.springframework.web.bind.annotation.RestController

@RestController
class ScheduledReportController(
    private val scheduledReportService: ScheduledReportService
) : ScheduledReportMapping {
    override fun get(id: String): ScheduledReportParameters {
        return scheduledReportService.findByName(id)
    }

    override fun getAll(): List<ScheduledReportParameters> {
        return scheduledReportService.findAll()
    }

    override fun schedule(scheduledReportParameters: ScheduledReportParameters): ScheduledReportParameters {
        scheduledReportService.schedule(scheduledReportParameters)
        return scheduledReportParameters
    }

    override fun unschedule(id: String) {
        scheduledReportService.unschedule(id)
    }
}