package com.electroducky.reporter.model

data class RawReport(
    val templateId: String,
    val variables: Map<String, String>
)
