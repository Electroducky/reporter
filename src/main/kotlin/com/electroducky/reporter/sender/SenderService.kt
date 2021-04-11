package com.electroducky.reporter.sender

import com.electroducky.reporter.report.Report

interface SenderService {
    val type: String
    fun send(report: Report, endpoint: String)
}