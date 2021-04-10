package com.electroducky.reporter.service

import com.electroducky.reporter.model.RawReport

interface SenderService {
    val type: String
    fun send(rawReport: RawReport, endpoint: String)
}