package com.electroducky.reporter.sender

import com.electroducky.reporter.common.logger
import com.electroducky.reporter.report.Report
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty("app.sender.mail.enabled")
class EmailSenderService(
    private val emailSender: JavaMailSender,
    @Value("\${spring.mail.username}") private val mailUsername: String,
    @Value("\${app.mail.subject}") private val mailSubject: String
) : SenderService {
    private val log = logger()

    override val type: String
        get() = "email"

    override fun send(report: Report, endpoint: String) {
        log.info("Sending report email to $endpoint")

        val message = SimpleMailMessage()
        message.setFrom(mailUsername)
        message.setTo(endpoint)
        message.setSubject(mailSubject)
        message.setText(report.message)

        emailSender.send(message)
    }
}