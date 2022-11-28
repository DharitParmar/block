package com.example.employeedirectory.core.data.employee

import com.example.employeedirectory.common.exception.Failure
import com.example.employeedirectory.common.exception.Failure.NoNetworkConnection
import com.example.employeedirectory.common.functional.Either
import com.example.employeedirectory.common.functional.Either.Left
import com.example.employeedirectory.common.functional.Either.Right
import com.example.employeedirectory.common.platform.ConnectionHandler
import com.example.employeedirectory.core.domain.employee.Employee
import com.example.employeedirectory.core.domain.employee.EmployeeRepository
import javax.inject.Inject

class DefaultEmployeeRepository @Inject constructor(
    private val employeeNetworkDataSource: EmployeeDataSource,
    private val connectionHandler: ConnectionHandler
) : EmployeeRepository {

    override suspend fun getEmployees(): Either<Failure, List<Employee>> {
        if (!connectionHandler.hasNetworkConnection()) {
            return Left(NoNetworkConnection)
        }

        val networkEmployees = employeeNetworkDataSource.getEmployees()

        val employees = networkEmployees.map { it.toDomainModel() }
        return Right(employees)
    }


}