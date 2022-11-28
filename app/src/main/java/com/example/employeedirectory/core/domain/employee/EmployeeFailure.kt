package com.example.employeedirectory.core.domain.employee

import com.example.employeedirectory.common.exception.Failure.FeatureFailure

sealed class EmployeeFailure: FeatureFailure() {

    object EmptyList: EmployeeFailure()
}