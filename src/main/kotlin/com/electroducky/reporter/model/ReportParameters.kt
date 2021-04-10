package com.electroducky.reporter.model

data class ReportParameters(
    val templateId: String,
    val variables: Map<String, String>
)
