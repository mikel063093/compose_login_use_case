package com.mike.baubap.domain

import app.cash.turbine.test
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
class InputValidationUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockRepository: ValidationRepository

    @InjectMockKs
    private lateinit var inputValidationUseCase: InputValidationUseCase

    private val initialInput = Input("") // Assuming the initial value is an empty string

    @Test
    fun `given initial input, should emit false`() = runTest {
        val userNameState = MutableStateFlow(initialInput)
        val passwordState = MutableStateFlow(initialInput)
        every { mockRepository.isUserNameValid(initialInput.value) } returns false
        every { mockRepository.isPasswordValid(initialInput.value) } returns false
        inputValidationUseCase(userNameState, passwordState).test {
            // Verify that the first value emitted is false
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `given invalid user name and password, should emit false`() = runTest {
        val invalidUserName = Input("invaliduser")
        val invalidPassword = Input("invalidpassword")
        every { mockRepository.isUserNameValid(invalidUserName.value) } returns false
        every { mockRepository.isPasswordValid(invalidPassword.value) } returns false

        val userNameState = MutableStateFlow(initialInput)
        val passwordState = MutableStateFlow(initialInput)

        userNameState.value = invalidUserName
        passwordState.value = invalidPassword
        inputValidationUseCase(userNameState, passwordState).test {
            // Emit invalid user name and password and verify that the value emitted is false
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `given valid user name and password, should emit true`() = runTest {
        val validUserName = Input("validuser")
        val validPassword = Input("validPassword123")
        every { mockRepository.isUserNameValid(validUserName.value) } returns true
        every { mockRepository.isPasswordValid(validPassword.value) } returns true

        val userNameState = MutableStateFlow(initialInput)
        val passwordState = MutableStateFlow(initialInput)

        userNameState.value = validUserName
        passwordState.value = validPassword
        inputValidationUseCase(userNameState, passwordState).test {
            // Emit valid user name and password and verify that the value emitted is true
            assertTrue(awaitItem())
        }
    }
}