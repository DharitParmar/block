package com.example.employeedirectory.core.domain.employee

import com.example.employeedirectory.common.exception.Failure
import com.example.employeedirectory.common.di.IoDispatcher
import com.example.employeedirectory.common.functional.Either
import com.example.employeedirectory.common.functional.Either.Left
import com.example.employeedirectory.common.functional.Either.Right
import com.example.employeedirectory.core.domain.AsyncInteractor
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetEmployees @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): AsyncInteractor<Unit, List<Employee>>(dispatcher) {

    override suspend fun execute(params: Unit): Either<Failure, List<Employee>> {
        val result = employeeRepository.getEmployees()

        // when success
        if(result is Right)  {
            // mark empty list as a failure
            return if (result.rightVal.isEmpty()) {
                Left(EmployeeFailure.EmptyList)
            } else {
                // otherwise sort by name
                val sorted = result.rightVal.sortedBy { it.name }
                Right(sorted)
            }
        }

        // otherwise return as is
        return result
    }

}