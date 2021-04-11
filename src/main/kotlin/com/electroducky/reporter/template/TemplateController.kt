package com.electroducky.reporter.template

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/template")
class TemplateController(
    private val templateService: TemplateService
) {

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): Template? {
        return templateService.findById(id)
    }

    @GetMapping
    fun getAll(): MutableIterable<Template> {
        return templateService.findAll()
    }

    @PostMapping
    fun create(@RequestBody template: Template): Template {
        return templateService.create(template)
    }

    @PutMapping
    fun update(@RequestBody template: Template): Template {
        return templateService.update(template)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        return templateService.delete(id)
    }
}