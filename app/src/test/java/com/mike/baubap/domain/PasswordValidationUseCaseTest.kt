package com.mike.baubap.domain

import app.cash.turbine.test
import app.cash.turbine.testIn
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PasswordValidationUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockRepository: ValidationRepository

    @InjectMockKs
    private lateinit var passwordValidationUseCase: PasswordValidationUseCase

    @Test
    fun `given initial state, should emit true`() = runTest {
        val initialState = Input("")
        val passwordState = MutableStateFlow(initialState)
        every { mockRepository.isPasswordValid(initialState.value) } returns true
        passwordValidationUseCase(passwordState).test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `given invalid password, should emit false`() = runTest {
        val invalidPassword = Input("inv")
        every { mockRepository.isPasswordValid(invalidPassword.value) } returns false
        val passwordState = MutableStateFlow(invalidPassword)
        passwordValidationUseCase(passwordState).test {
            // Emit invalid password and verify that the value emitted is false
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `given valid password, should emit true`() = runTest {
        val validPassword = Input("validPassword123")
        every { mockRepository.isPasswordValid(validPassword.value) } returns true
        val passwordState = MutableStateFlow(validPassword)
        passwordValidationUseCase(passwordState).test {
            // Emit valid password and verify that the value emitted is true
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `given multiple updates, should emit correct values`() = runTest {
        val validPassword = Input("validPassword123")
        every { mockRepository.isPasswordValid(validPassword.value) } returns true
        val passwordState = MutableStateFlow(validPassword)
        val turbine = passwordValidationUseCase(passwordState).testIn(backgroundScope)
        // Emit initial value and verify that the first value emitted is true
        assertTrue(turbine.awaitItem())
        val invalidPassword = Input("12")
        every { mockRepository.isPasswordValid(invalidPassword.value) } returns false
        passwordState.value = invalidPassword
        // Emit invalid password and verify that the value emitted is false
        assertFalse(turbine.awaitItem())
        passwordState.value = validPassword
        // Emit valid password and verify that the value emitted is true
        assertTrue(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }
}
