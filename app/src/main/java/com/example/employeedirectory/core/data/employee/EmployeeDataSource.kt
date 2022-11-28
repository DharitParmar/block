package com.example.employeedirectory.core.data.employee

interface EmployeeDataSource {

    suspend fun getEmployees(): List<NetworkEmployee>
}