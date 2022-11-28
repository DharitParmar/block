package com.example.employeedirectory.core.domain.employee

import com.example.employeedirectory.common.exception.Failure
import com.example.employeedirectory.common.functional.Either

interface EmployeeRepository {

    suspend fun getEmployees(): Either<Failure, List<Employee>>
}