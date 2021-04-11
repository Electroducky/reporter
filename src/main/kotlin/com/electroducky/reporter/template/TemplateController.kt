package com.electroducky.reporter.template

import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController(
    private val templateService: TemplateService
) : TemplateMapping {

    override fun get(id: String): Template? {
        return templateService.findById(id)
    }

    override fun getAll(): MutableIterable<Template> {
        return templateService.findAll()
    }

    override fun create(template: Template): Template {
        return templateService.create(template)
    }

    override fun update(template: Template): Template {
        return templateService.update(template)
    }

    override fun delete(id: String) {
        return templateService.delete(id)
    }
}