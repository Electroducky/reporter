package com.electroducky.reporter.service.senders

import com.electroducky.reporter.model.RawReport
import com.electroducky.reporter.service.SenderService
import org.springframework.stereotype.Service

@Service
class HttpSenderService() : SenderService {
    override val type: String get() = "Http"
    override fun send(rawReport: RawReport, endpoint: String) {
        TODO("Not yet implemented")
    }
}