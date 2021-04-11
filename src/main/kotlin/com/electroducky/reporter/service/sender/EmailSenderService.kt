package com.electroducky.reporter.service.sender

import com.electroducky.reporter.model.Report
import com.electroducky.reporter.service.SenderService
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailSenderService(
    private val emailSender: JavaMailSender,
    @Value("\${spring.mail.username}") private val mailUsername: String,
    @Value("\${app.mail.subject}") private val mailSubject: String
) : SenderService {

    override val type: String
        get() = "email"

    override fun send(report: Report, endpoint: String) {
        val message = SimpleMailMessage()
        message.setFrom(mailUsername)
        message.setTo(endpoint)
        message.setSubject(mailSubject)
        message.setText(report.message)

        emailSender.send(message);
    }
}