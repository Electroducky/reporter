package com.electroducky.reporter.report

data class ReportParameters(
    val templateId: String,
    val variables: Map<String, String>
)
