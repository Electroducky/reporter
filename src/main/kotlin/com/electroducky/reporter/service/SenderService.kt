package com.electroducky.reporter.service

import com.electroducky.reporter.model.Report

interface SenderService {
    val type: String
    fun send(report: Report, endpoint: String)
}