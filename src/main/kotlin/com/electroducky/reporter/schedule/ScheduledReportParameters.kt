package com.electroducky.reporter.schedule

data class ScheduledReportParameters(
    val name: String,
    val cronExpression: String,
    val templateId: String,
    val variables: Map<String, String>
)
