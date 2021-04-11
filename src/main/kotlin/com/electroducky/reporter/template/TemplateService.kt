package com.electroducky.reporter.template

import org.springframework.stereotype.Service

@Service
class TemplateService(
    private val templateRepository: TemplateRepository,
    private val templateRendererService: TemplateRendererService
) {

    fun create(template: Template): Template {
        templateRendererService.validateTemplate(template.templateText)
        return templateRepository.save(template)
    }

    fun update(template: Template): Template {
        templateRendererService.validateTemplate(template.templateText)
        return templateRepository.save(template)
    }

    fun delete(templateId: String) {
        templateRepository.deleteById(templateId)
    }

    fun findById(templateId: String): Template {
        return templateRepository.findById(templateId).get()
    }

    fun findAll(): MutableIterable<Template> {
        return templateRepository.findAll()
    }
}