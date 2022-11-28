package com.example.employeedirectory.core.data.employee

import retrofit2.http.GET

interface RetrofitEmployeeNetworkApi {

    companion object {

        private const val EMPLOYEES = "employees.json"
    }

    @GET(EMPLOYEES)
    suspend fun getEmployees(): BaseNetworkEmployee
}