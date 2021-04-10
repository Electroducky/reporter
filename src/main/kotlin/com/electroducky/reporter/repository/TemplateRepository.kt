package com.electroducky.reporter.repository

import com.electroducky.reporter.model.Template
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository : CrudRepository<Template, String> {
}