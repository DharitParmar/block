package com.example.employeedirectory.presentation.employee

import com.example.employeedirectory.core.domain.employee.Employee

sealed class EmployeeViewState() {
    object Loading: EmployeeViewState()
    data class EmployeeList(val list: List<Employee>): EmployeeViewState()
    data class EmployeeError(val msgID: Int): EmployeeViewState()
}