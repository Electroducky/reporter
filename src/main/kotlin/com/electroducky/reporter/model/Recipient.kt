package com.electroducky.reporter.model

import javax.persistence.Embeddable

@Embeddable
class Recipient(
    val type: String,
    val endpoint: String
)