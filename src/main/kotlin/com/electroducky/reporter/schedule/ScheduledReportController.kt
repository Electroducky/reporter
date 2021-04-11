package com.electroducky.reporter.schedule

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/scheduledreport")
class ScheduledReportController(
    private val scheduledReportService: ScheduledReportService
) {
    @GetMapping("{id}")
    fun get(@PathVariable id: String): ScheduledReportParameters {
        return scheduledReportService.findByName(id)
    }

    @GetMapping
    fun getAll(): List<ScheduledReportParameters> {
        return scheduledReportService.findAll()
    }

    @PostMapping
    fun schedule(@RequestBody scheduledReportParameters: ScheduledReportParameters): ScheduledReportParameters {
        scheduledReportService.schedule(scheduledReportParameters)
        return scheduledReportParameters
    }

    @DeleteMapping("{id}")
    fun unschedule(@PathVariable id: String) {
        scheduledReportService.unschedule(id)
    }
}