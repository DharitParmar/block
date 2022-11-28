package com.example.employeedirectory.core.data.employee

import com.example.employeedirectory.core.domain.employee.Employee
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkEmployee(
    val biography: String,
    val email_address: String,
    val employee_type: String,
    val full_name: String,
    val phone_number: String,
    val photo_url_large: String,
    val photo_url_small: String,
    val team: String,
    val uuid: String
) {

    fun toDomainModel(): Employee {
        return Employee(
            name = full_name,
            team = team,
            photoUrlSmall = photo_url_small
        )
    }
}

@JsonClass(generateAdapter = true)
data class BaseNetworkEmployee(
    val employees: List<NetworkEmployee>
)