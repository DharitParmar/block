package com.example.employeedirectory.core.domain.employee

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    val name: String,
    val team: String,
    val photoUrlSmall: String
)
