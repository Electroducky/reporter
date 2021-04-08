package com.electroducky.reporter.service

import com.electroducky.reporter.model.entity.Template
import com.electroducky.reporter.repository.TemplateRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TemplateService(
    private val templateRepository: TemplateRepository,
) {

    fun create(template: Template): Template {
        return templateRepository.save(template)
    }

    fun update(template: Template): Template {
        return templateRepository.save(template)
    }

    fun delete(templateId: String) {
        templateRepository.deleteById(templateId)
    }

    fun findById(templateId: String): Template? {
        return templateRepository.findByIdOrNull(templateId)
    }

    fun findAll(): MutableIterable<Template> {
        return templateRepository.findAll()
    }
}