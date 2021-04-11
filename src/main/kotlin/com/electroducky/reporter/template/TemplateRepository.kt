package com.electroducky.reporter.template

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository : CrudRepository<Template, String> {
}