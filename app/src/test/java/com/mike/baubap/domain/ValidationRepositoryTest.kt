package com.mike.baubap.domain

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidationRepositoryTest {

    private var mockSuccessValue: String = "qwert"

    private var mockFailedValue: String = "123"

    @InjectMocks
    private lateinit var validationRepositoryImpl: ValidationRepositoryImpl

    @Test
    fun `isUserNameValid returns true if the length of the username is greater than or equal to the minimum length`() {
        val result = validationRepositoryImpl.isUserNameValid(mockSuccessValue)
        assertTrue(result)
    }

    @Test
    fun `isUserNameValid returns false if the length of the username is less than the minimum length`() {
        val result = validationRepositoryImpl.isUserNameValid(mockFailedValue)
        assertFalse(result)
    }

    @Test
    fun `isPasswordValid returns true if the length of the password is greater than or equal to the minimum length`() {
        val result = validationRepositoryImpl.isPasswordValid(mockSuccessValue)
        assertTrue(result)
    }

    @Test
    fun `isPasswordValid returns false if the length of the password is less than the minimum length`() {
        val result = validationRepositoryImpl.isPasswordValid(mockFailedValue)
        assertFalse(result)
    }
}