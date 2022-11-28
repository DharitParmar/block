package com.example.employeedirectory.core.data.employee

import javax.inject.Inject

class EmployeeNetworkDataSource @Inject constructor(
    private val retrofitEmployeeNetworkApi: RetrofitEmployeeNetworkApi
): EmployeeDataSource {

    override suspend fun getEmployees(): List<NetworkEmployee> {
        return retrofitEmployeeNetworkApi.getEmployees().employees
    }
}