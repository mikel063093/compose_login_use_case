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
class UserNameValidationUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockRepository: ValidationRepository

    @InjectMockKs
    private lateinit var userNameValidationUseCase: UserNameValidationUseCase

    @Test
    fun `invoke returns false if username is less than the minimum length`() =
        runTest {
            // Given
            val input = MutableStateFlow(Input(""))
            every { mockRepository.isUserNameValid(any()) } returns false
            // When
            userNameValidationUseCase(input).test {
                // Then
                assertFalse(awaitItem())
            }
        }

    @Test
    fun `invoke returns true if username is greater than or equal to the minimum length`() =
        runTest {
            // Given
            val input = MutableStateFlow(Input("abcd"))
            every { mockRepository.isUserNameValid(any()) } returns true
            // When
            userNameValidationUseCase(input).test {
                // Then
                assertTrue(awaitItem())
            }
        }

    @Test
    fun `invoke returns true for initial value`() =
        runTest {
            // Given
            val input = MutableStateFlow(Input())
            every { mockRepository.isUserNameValid(any()) } returns true
            // When
            userNameValidationUseCase(input).test {
                // Then
                assertTrue(awaitItem())
            }
        }
}