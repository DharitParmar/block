package com.example.employeedirectory.presentation.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.R
import com.example.employeedirectory.common.exception.Failure
import com.example.employeedirectory.common.exception.Failure.NoNetworkConnection
import com.example.employeedirectory.common.exception.Failure.ServerError
import com.example.employeedirectory.common.platform.BaseViewModel
import com.example.employeedirectory.core.domain.employee.Employee
import com.example.employeedirectory.core.domain.employee.EmployeeFailure
import com.example.employeedirectory.core.domain.employee.GetEmployees
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val getEmployees: GetEmployees
): BaseViewModel() {

    private var _employees = MutableLiveData<EmployeeViewState>().apply { EmployeeViewState.Loading  }
    val employees: LiveData<EmployeeViewState> = _employees

    val isRefreshing: LiveData<Boolean> = Transformations.map(_employees){
        it is EmployeeViewState.Loading
    }

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        viewModelScope.launch {
            getEmployees(Unit).also {
                it.fold(::handleEmployeesFailure, ::handleEmployeesList)
            }
        }
    }

    private fun handleEmployeesList(employees: List<Employee>) {
        _employees.value = EmployeeViewState.EmployeeList(employees)
    }

    private fun handleEmployeesFailure(failure: Failure) {
        val error: Int =
            when (failure) {
                EmployeeFailure.EmptyList -> R.string.failure_employees_list_unavailable
                NoNetworkConnection -> R.string.failure_network_connection
                ServerError -> R.string.failure_server_error
                else -> { R.string.failure_server_error }
            }

        _employees.value = EmployeeViewState.EmployeeError(error)
    }

    fun refreshEmployees() = loadEmployees()
}