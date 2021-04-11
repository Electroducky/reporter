package com.electroducky.reporter.model

import javax.persistence.*

@Entity
class Template(
    @Id
    val templateId: String,
    val templateText: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipient", joinColumns = [JoinColumn(name = "template_id")])
    val recipients: List<Recipient>
)
