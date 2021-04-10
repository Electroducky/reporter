package com.electroducky.reporter.model

import javax.persistence.Embeddable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

//@Entity
@Embeddable
class Recipient(
    val type: String,
    val endpoint: String,

//    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null
)