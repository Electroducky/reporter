package com.electroducky.reporter.model

import javax.persistence.*

@Entity
class Template(
    @Id val templateId: String,
    val template: String,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "recipient", joinColumns = [JoinColumn(name = "template_id")])
    val recipients: List<Recipient>
)
