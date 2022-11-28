package com.example.employeedirectory.core.domain.employee

import com.example.employeedirectory.BaseUnitTest
import com.example.employeedirectory.common.exception.Failure.ServerError
import com.example.employeedirectory.common.functional.Either.Left
import com.example.employeedirectory.common.functional.Either.Right
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetEmployeesTest: BaseUnitTest() {

    // subject under test
    private lateinit var getEmployees: GetEmployees
    private val coroutineDispatcher: CoroutineDispatcher = StandardTestDispatcher()
    @MockK
    private lateinit var employeesRepository: EmployeeRepository


    @Before
    fun setup() {
        getEmployees = GetEmployees(employeesRepository, coroutineDispatcher)
    }

    @Test
    fun `should get data from repository`() = runTest {
        // given repo with employee data
        coEvery { employeesRepository.getEmployees() } returns Right(emptyList())
        // load employees
        getEmployees.execute(Unit)
        // then verify data being returned
        coVerify(exactly = 1) { employeesRepository.getEmployees() }
    }

    @Test
    fun `should sort employees by name`() = runTest {
        // given repo with 2 employees
        val employees = listOf(
            Employee("test2", "testTeam2", ""),
            Employee("test1", "testTeam1", "")
        )
        val sorted = employees.sortedBy { it.name }
        coEvery { employeesRepository.getEmployees() } returns Right(employees)
        // load employees
        val result = getEmployees.execute(Unit)
        //verify that employees are sorted by name
        assertEquals((result as Right).rightVal, sorted)
    }

    @Test
    fun `should return failure when empty list from repository`() = runTest {
        // given repo with employee data
        coEvery { employeesRepository.getEmployees() } returns Right(emptyList())
        // load employees
        val result = getEmployees.execute(Unit)
        // then verify data being returned
        coVerify(exactly = 1) { employeesRepository.getEmployees() }
        assertTrue(result is Left)
        assertTrue((result as Left).leftVal == EmployeeFailure.EmptyList)
    }

    @Test
    fun `get employees should catch exceptions`()  = runTest {
        // load employees
        val result = getEmployees(Unit)
        // should catch exception
        assertTrue(result is Left)
        assertTrue((result as Left).leftVal is ServerError)
    }

}