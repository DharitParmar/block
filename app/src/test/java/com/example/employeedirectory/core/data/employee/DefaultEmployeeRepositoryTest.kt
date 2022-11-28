package com.example.employeedirectory.core.data.employee

import com.example.employeedirectory.BaseUnitTest
import com.example.employeedirectory.common.exception.Failure.NoNetworkConnection
import com.example.employeedirectory.common.functional.Either.Left
import com.example.employeedirectory.common.functional.Either.Right
import com.example.employeedirectory.common.platform.ConnectionHandler
import com.example.employeedirectory.core.domain.employee.Employee
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DefaultEmployeeRepositoryTest: BaseUnitTest() {
    // subject under test
    private lateinit var defaultEmployeeRepository: DefaultEmployeeRepository
    @MockK
    lateinit var employeeNetworkDataSource: EmployeeNetworkDataSource
    @MockK
    lateinit var connectionHandler: ConnectionHandler

    @Before
    fun setup() {
        defaultEmployeeRepository =
            DefaultEmployeeRepository(employeeNetworkDataSource, connectionHandler)
    }

    @Test
    fun `should get employees list from network`() = runTest {
        // given Network data
        val networkList =
            listOf(
                NetworkEmployee(
                    "test1",
                    "team1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1",
                    "test1"
                )
            )
        val domainList = listOf(Employee("test1", "test1", "test1"))
        coEvery { connectionHandler.hasNetworkConnection() } returns true
        coEvery { employeeNetworkDataSource.getEmployees() } returns networkList
        // get employees
        val employees = defaultEmployeeRepository.getEmployees()
        // should return list of domain employees
        assertEquals(employees, Right(domainList))
    }

    @Test
    fun `get employees should return network failure when no connection`() = runTest {
        // given network error
        every { connectionHandler.hasNetworkConnection() } returns false
        // get employees
        val employees = defaultEmployeeRepository.getEmployees()
        // should not call remote data source
        coVerify (exactly = 0) { employeeNetworkDataSource.getEmployees() }
        // should return network connection failure
        assertTrue(employees is Left)
        assertTrue((employees as Left).leftVal is NoNetworkConnection)
    }
}