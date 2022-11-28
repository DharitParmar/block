package com.example.employeedirectory.common.exception

sealed class Failure {

    object NoNetworkConnection: Failure()
    object ServerError: Failure()

    abstract class FeatureFailure(): Failure()
}
