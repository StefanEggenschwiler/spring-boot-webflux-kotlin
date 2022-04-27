package com.example.reactive.kotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Person(
    @Id val id: Int?,
    val surname: String,
    val name: String,
    val age: Int
)