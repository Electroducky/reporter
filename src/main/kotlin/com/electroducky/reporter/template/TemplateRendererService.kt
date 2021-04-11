package com.electroducky.reporter.template

import com.electroducky.reporter.report.Report
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TemplateRendererService(
    @Value("\${app.template.openingSeparator}") private val openingSeparator: String,
    @Value("\${app.template.closingSeparator}") private val closingSeparator: String
) {
    fun render(reportParameters: Map<String, String>, template: String): Report {
        val builder = StringBuilder()

        var processingPointer = 0
        while (processingPointer != template.length) {
            val (opening, closing) = template.findNextPlaceholder(
                openingSeparator,
                closingSeparator,
                processingPointer
            )

            val appendEnd = if (opening == -1) template.length else opening
            val appendix = template.substring(processingPointer, appendEnd)
                .unescape(openingSeparator)
                .unescape(closingSeparator)
            builder.append(appendix)

            if (opening == -1) {
                break
            }

            val placeholder = template.substring(opening + 1, closing)
            val (key, type) = placeholder.split(":").map { it.trim() }

            val value = reportParameters[key]
                ?: throw IllegalStateException("Placeholder $placeholder is not provided to the template")

            val validationResult = TemplateParameterType.valueOf(type).validate(value)
            if (!validationResult) {
                throw IllegalStateException("Parameter $placeholder type validation failed")
            }
            builder.append(value)

            processingPointer = closing + 1
        }

        return Report(builder.toString())
    }

    fun validateTemplate(template: String) {
        var lastProcessedPointer = 0
        while (lastProcessedPointer != template.length) {
            val (opening, closing) = template.findNextPlaceholder(
                openingSeparator,
                closingSeparator,
                lastProcessedPointer
            )
            if (opening == -1) {
                break
            }

            val placeholder = template.substring(opening + 1, closing)
            TemplateParameterType.valueOf(placeholder.split(":")[1].trim())

            lastProcessedPointer = closing + 1
        }
    }
}