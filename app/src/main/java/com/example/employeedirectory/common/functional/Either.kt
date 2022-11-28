package com.example.employeedirectory.common.functional

// sources:
// https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-6-error-handling-with-try/
// https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-7-the-either-type/
// https://proandroiddev.com/kotlins-nothing-type-946de7d464fb

// Left represents Failure(Error), Right represents Success
sealed class Either<out L, out R> {

    data class Left<out L>(val leftVal: L): Either<L, Nothing>()
    data class Right<out R>(val rightVal: R): Either<Nothing, R>()

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(leftVal)
            is Right -> fnR(rightVal)
        }

}