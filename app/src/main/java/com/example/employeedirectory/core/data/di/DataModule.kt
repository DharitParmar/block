package com.example.employeedirectory.core.data.di

import com.example.employeedirectory.common.platform.ConnectionHandler
import com.example.employeedirectory.core.data.employee.EmployeeDataSource
import com.example.employeedirectory.core.data.employee.DefaultEmployeeRepository
import com.example.employeedirectory.core.domain.employee.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesEmployeeRepository(
        @NetworkEmployeeDataSource employeeNetworkDataSource: EmployeeDataSource,
        connectionHandler: ConnectionHandler
    ): EmployeeRepository =
        DefaultEmployeeRepository(employeeNetworkDataSource, connectionHandler)
}