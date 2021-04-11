package com.electroducky.reporter.schedule

import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/scheduledreport")
interface ScheduledReportMapping {
    @GetMapping("{id}")
    fun get(@PathVariable id: String): ScheduledReportParameters

    @GetMapping
    fun getAll(): List<ScheduledReportParameters>

    @PostMapping
    fun schedule(@RequestBody scheduledReportParameters: ScheduledReportParameters): ScheduledReportParameters

    @DeleteMapping("{id}")
    fun unschedule(@PathVariable id: String)
}