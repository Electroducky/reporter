package com.electroducky.reporter.service

import com.electroducky.reporter.model.RawReport
import com.electroducky.reporter.model.Report
import org.springframework.stereotype.Service

@Service
class TemplateRendererService {
    fun render(rawReport: RawReport, template: String): Report {
        TODO("Not yet implemented")
    }
}