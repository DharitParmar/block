package com.example.employeedirectory

import io.mockk.MockKAnnotations
import org.junit.Rule
import org.junit.rules.TestRule

abstract class BaseUnitTest {

    @Rule
    @JvmField
    val injectMocksRule = TestRule { statement, _ ->
        MockKAnnotations.init(this@BaseUnitTest, relaxUnitFun = true)
        statement
    }
}