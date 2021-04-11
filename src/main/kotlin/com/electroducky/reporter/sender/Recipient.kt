package com.electroducky.reporter.sender

import javax.persistence.Embeddable

@Embeddable
class Recipient(
    val type: String,
    val endpoint: String
)