package com.electroducky.reporter.schedule

import com.electroducky.reporter.common.logger
import com.electroducky.reporter.report.ReportParameters
import com.electroducky.reporter.report.ReportService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.quartz.*
import org.quartz.JobKey
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service


@Service
class ScheduledReportService(
    private val scheduler: Scheduler,
    private val reportService: ReportService,
    private val objectMapper: ObjectMapper
) {
    private val log = logger()

    fun schedule(scheduledReportParameters: ScheduledReportParameters) {
        reportService.validateReportParameters(
            ReportParameters(
                scheduledReportParameters.templateId,
                scheduledReportParameters.variables
            )
        )

        val jobDetail = buildJobDetail(scheduledReportParameters)
        val trigger = buildTrigger(scheduledReportParameters)

        scheduler.scheduleJob(jobDetail, trigger)
        log.info(
            "Scheduled report job ${scheduledReportParameters.name} by " +
                    "${scheduledReportParameters.cronExpression} with report ${scheduledReportParameters.templateId}"
        )
    }

    fun unschedule(scheduledReportName: String) {
        scheduler.deleteJob(JobKey.jobKey(scheduledReportName, schedulingGroup))
        log.info("Unscheduled report job $scheduledReportName")
    }

    fun findAll(): List<ScheduledReportParameters> {
        return scheduler.getJobKeys(GroupMatcher.jobGroupEquals(schedulingGroup))
            .map { findByName(it.name) }
    }

    fun findByName(scheduledReportName: String): ScheduledReportParameters {
        val jobKey = JobKey.jobKey(scheduledReportName, schedulingGroup)
        val persistParamsObject = scheduler.getJobDetail(jobKey).jobDataMap[ScheduledReportJob.scheduledReportData]
            ?: throw IllegalStateException("Scheduled job key was no found: $scheduledReportName")

        val persistParams = persistParamsObject as String
        return objectMapper.readValue(persistParams)
    }

    private fun buildJobDetail(scheduledReportParameters: ScheduledReportParameters): JobDetail {
        val persistParams = objectMapper.writeValueAsString(scheduledReportParameters)
        val jobDataMap = JobDataMap(mapOf(ScheduledReportJob.scheduledReportData to persistParams))

        return JobBuilder.newJob(ScheduledReportJob::class.java)
            .withIdentity(scheduledReportParameters.name, schedulingGroup)
            .usingJobData(jobDataMap)
            .build()
    }

    private fun buildTrigger(scheduledReportParameters: ScheduledReportParameters): CronTrigger {
        return TriggerBuilder.newTrigger()
            .withIdentity(scheduledReportParameters.name, schedulingGroup)
            .startNow()
            .withSchedule(
                CronScheduleBuilder
                    .cronSchedule(scheduledReportParameters.cronExpression)
                    .withMisfireHandlingInstructionDoNothing()
            )
            .build()
    }

    companion object {
        private const val schedulingGroup = "report"
    }
}