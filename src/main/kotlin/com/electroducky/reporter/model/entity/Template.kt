package com.electroducky.reporter.model.entity

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Template(
    @Id val templateId: String,
    val template: String,
    @ElementCollection val recipients: List<String>
)
