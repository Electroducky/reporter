package com.electroducky.reporter.sender

import com.electroducky.reporter.report.Report
import org.springframework.stereotype.Service

@Service
class DispatcherService(
    senders: List<SenderService>
) {
    private val senderMap = senders.associateBy { it.type }

    fun dispatch(report: Report, recipients: List<Recipient>) {
        recipients.forEach {
            senderMap[it.type]?.send(report, it.endpoint) ?: throw IllegalStateException("Unsupported recipient type")
        }
    }
}