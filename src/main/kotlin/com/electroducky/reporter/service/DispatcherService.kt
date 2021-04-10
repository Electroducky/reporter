package com.electroducky.reporter.service

import com.electroducky.reporter.model.Report
import org.springframework.stereotype.Service

@Service
class DispatcherService(
    val senders: List<SenderService>
) {
    fun coordinate(report: Report, senderType: List<String>) {
        TODO("Not yet implemented")
    }
}