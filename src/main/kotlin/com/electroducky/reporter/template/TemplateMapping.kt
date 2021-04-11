package com.electroducky.reporter.template

import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/template")
interface TemplateMapping {
    @GetMapping("/{id}")
    fun get(@PathVariable id: String): Template?

    @GetMapping
    fun getAll(): MutableIterable<Template>

    @PostMapping
    fun create(@RequestBody template: Template): Template

    @PutMapping
    fun update(@RequestBody template: Template): Template

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String)
}