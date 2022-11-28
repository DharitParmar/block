package com.example.employeedirectory.core.domain

import com.example.employeedirectory.common.exception.Failure
import com.example.employeedirectory.common.exception.Failure.ServerError
import com.example.employeedirectory.common.functional.Either
import com.example.employeedirectory.common.functional.Either.Left
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

/* base contract that runs every usecase/interactor in background -
which intern runs domain and data into background
*/
abstract class AsyncInteractor<in InputParams, out FinalResult>(
    private val dispatcher: CoroutineDispatcher
) where InputParams: Any, FinalResult: Any {

    // every use-case will implement it's operation
    abstract suspend fun execute(params: InputParams): Either<Failure, FinalResult>

    suspend operator fun invoke(params: InputParams): Either<Failure, FinalResult> =
        try {
            withContext(dispatcher) {
                execute(params)
            }
        } catch (e: Exception) {
            Timber.e("uncaught exception: $e")
            Left(ServerError) // for brevity just marked as a server error
        }
}